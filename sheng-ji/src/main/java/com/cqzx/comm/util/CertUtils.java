package com.cqzx.comm.util;




import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import com.cqzx.comm.util.cert.PKCS12KeyStore;
import com.cqzx.comm.util.cert.SM2;
import com.cqzx.comm.util.cert.SM2PrivateKeyParsing;
import com.cqzx.comm.util.cert.SM2Result;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.X509CertificateObject;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;




@SuppressWarnings("deprecation")
public class CertUtils {
	
	public static String[] sm2_param = {
		"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF",// p,0
		"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC",// a,1
		"28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93",// b,2
		"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123",// n,3
		"32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7",// gx,4
		"BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0" // gy,5
	};
	
	private static byte[] bytesAfter32(byte[] src) {
		byte[] cc = new byte[32];
		for (int i = 0; i < 32; i++) {
			cc[i] = src[i];
		}
		return cc;
	}

	private static byte[] bytesBefore32(byte[] src) {
		byte[] cc = new byte[32];
		for (int i = 0; i < 32; i++) {
			cc[i] = src[i + 32];
		}
		return cc;
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	private static String rDN(String dn)
	{
		String rev="";
		String []dnlist=dn.split(",");
		for(int i=dnlist.length-1;i>=0;i--)
		{
			if(i!=0)
			{
				rev+=dnlist[i]+",";
			}
			else
			{
				rev+=dnlist[i];
			}
		}
		
		return rev;
	}

	
	/**
	 * 
	 * @param Dn 证书主题
	 * @param pubkey 证书公钥
	 * @param pubkeyalg 证书公钥算法
	 * @return 证书请求信息
	 * @throws Exception
	 */
	private static String GenCertificationRequestInfo(String Dn, String pubkey,
			String pubkeyalg) throws Exception {
		String rv = "";
		try {
			CertificationRequestInfo cri = null;
			X500Name subject = null;
			SubjectPublicKeyInfo subjectPKInfo = null;
			ASN1Set attributes = null;
			attributes = ASN1Set.getInstance(null);

			byte[] nullset = new byte[2];
			nullset[0] = (byte) 0xa0;
			nullset[1] = 0;
			attributes = ASN1Set.getInstance((ASN1TaggedObject) attributes
					.fromByteArray(nullset), false);
			subject = new X500Name(Dn);
			AlgorithmIdentifier alg = null;
			if (pubkeyalg.toUpperCase().equals("RSA")) {
				ASN1ObjectIdentifier oid1 = new ASN1ObjectIdentifier(
						"1.2.840.113549.1.1.1");
				alg = new AlgorithmIdentifier(oid1, new DERNull());
			} else if (pubkeyalg.toUpperCase().equals("SM2")) {
				ASN1ObjectIdentifier oid1 = new ASN1ObjectIdentifier(
						"1.2.840.10045.2.1");
				ASN1ObjectIdentifier oid2 = new ASN1ObjectIdentifier(
						"1.2.156.10197.1.301");
				alg = new AlgorithmIdentifier(oid1, oid2);
			} else {
				throw new Exception("Not supported by the algorithm");
			}
			byte[] pubkeybyte = Base64.decode(pubkey);
			if (pubkeyalg.toUpperCase().equals("SM2")
					&& pubkeybyte.length == 64) {
				byte[] pubkeybyte2 = new byte[65];
				pubkeybyte2[0] = 4;
				for (int i = 0; i < 32; i++) {
					pubkeybyte2[1 + i] = pubkeybyte[i];
					pubkeybyte2[33 + i] = pubkeybyte[i + 32];
				}
				pubkeybyte = pubkeybyte2;
			}
			subjectPKInfo = new SubjectPublicKeyInfo(alg, pubkeybyte);
			cri = new CertificationRequestInfo(subject, subjectPKInfo,
					attributes);
			rv = Base64.toBase64String(cri.getEncoded());
		} catch (Exception e) {
			throw e;
		}
		return rv;
	}
	
	/**
	 * 
	 * @param Pkcs8 私钥的pkcs8格式
	 * @param data 待签名数据
	 * @param Alg 签名的hash算法 注意rsa为sha1、sha256 sm2为sm3
	 * @return 签名值
	 * @throws Exception
	 */
	public static String SignP1_2(String Pkcs8, byte[] data, String Alg)
			throws Exception {

		// //1.2.840.10045.2.1 //1.2.840.113549.1.1.1
		try {
			Security.addProvider(new BouncyCastleProvider());
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64
					.decode(Pkcs8));
			ByteArrayInputStream bIn = new ByteArrayInputStream(Base64
					.decode(Pkcs8));
			
			
			ASN1InputStream dIn = new ASN1InputStream(bIn);
			ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
			PrivateKeyInfo pri = new PrivateKeyInfo(seq);
			String algid = pri.getAlgorithmId().getAlgorithm().getId();
			if (algid.equals("1.2.840.113549.1.1.1")) // rsa
			{
				KeyFactory keyf = KeyFactory.getInstance("RSA");
				PrivateKey priv = keyf.generatePrivate(priPKCS8);
				
				//System.out.println("priv:"+priv);
				
				Signature signet = Signature.getInstance("RSA");
				signet.initSign(priv);
				Digest messageDigest = null;
				String hashoid = "";
				if (Alg.toUpperCase().equals("SHA1")
						|| Alg.toUpperCase().equals("SHA-1")) {
					messageDigest = new SHA1Digest(); //
					hashoid = "1.3.14.3.2.26"; //
				} else if (Alg.toUpperCase().equals("SHA256")
						|| Alg.toUpperCase().equals("SHA-256")) {
					messageDigest = new SHA256Digest(); //
					hashoid = "2.16.840.1.101.3.4.2.1";
				} else if (Alg.toUpperCase().equals("SHA512")
						|| Alg.toUpperCase().equals("SHA-512")) {
					messageDigest = new SHA512Digest(); //
					hashoid = "2.16.840.1.101.3.4.2.3";
				} else if (Alg.toUpperCase().equals("SM3")) {
					messageDigest = new SM3Digest(); //
				} else {
					throw new Exception("The hash algorithm of the invalid");
				}

				byte[] retValue = new byte[messageDigest.getDigestSize()];
				messageDigest.update(data, 0, data.length);
				messageDigest.doFinal(retValue, 0);
				ASN1EncodableVector digestAlgorithms = new ASN1EncodableVector();
				ASN1EncodableVector algos = new ASN1EncodableVector();
				algos.add(new DERObjectIdentifier((String) hashoid));
				algos.add(DERNull.INSTANCE);
				digestAlgorithms.add(new DERSequence(algos));
				DEROctetString dos = new DEROctetString(retValue);
				digestAlgorithms.add(dos);
				DERSequence digestAlgorithmsseq = new DERSequence(
						digestAlgorithms);
				signet.update(digestAlgorithmsseq.getEncoded());
				byte[] signed = signet.sign();
				return new String(Base64.encode(signed));
			} else if (algid.equals("1.2.840.10045.2.1")) // ecc
			{
				
				KeyFactory keyf = KeyFactory.getInstance("ECDSA");
				PrivateKey priv = keyf.generatePrivate(priPKCS8);
				ECPrivateKey sKey3 = (ECPrivateKey) priv;
				SM2PrivateKeyParsing smpp=new SM2PrivateKeyParsing();
				String pubkey=smpp.KeyParsing(sKey3.getS());
				
				Digest messageDigest = null;
				if (Alg.toUpperCase().equals("SM3")) {
					messageDigest = new SM3Digest();
				} else {
					throw new Exception("The hash algorithm of the invalid");
				}
				byte[] retValue = new byte[messageDigest.getDigestSize()];

				SM2 sm2 = SM2.Instance();
				ECPoint userKey = null;
				// 閸欐牕鍙曢柦锟�
				byte[] publickey = Base64.decode(pubkey);
				byte[] x = bytesAfter32(publickey);
				byte[] y = bytesBefore32(publickey);

				String strx = bytesToHexString(x); // 閸忥拷
				String stry = bytesToHexString(y); // 閸忥拷
				// /
				userKey = sm2.generationECPointPublickey(strx, stry);
				byte z[] = sm2.Sm2GetZ("1234567812345678".getBytes(), userKey);
				messageDigest.update(z, 0, z.length);
				messageDigest.update(data, 0, data.length);
				messageDigest.doFinal(retValue, 0);
				SM2Result sm2Ret = new SM2Result();
				sm2.Sm2Sign(retValue, sKey3.getS(), userKey, sm2Ret);

				byte[] rb = sm2Ret.r.toByteArray();
				byte[] sb = sm2Ret.s.toByteArray();
				DERInteger derr = new DERInteger(rb);
				DERInteger ders = new DERInteger(sb);
				ASN1EncodableVector aev = new ASN1EncodableVector();
				aev.add(derr);
				aev.add(ders);
				DERSequence dseq = new DERSequence(aev);
				return new String(Base64.encode(dseq.getEncoded()));
				
			} else {
				throw new Exception("Not supported by the algorithm");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param dn 证书主题
	 * @param pkcs8 证书私钥的pkcs8格式
	 * @param hashalg 签名的hash算法 注意rsa为sha1、sha256 sm2为sm3
	 * @return 证书请求即pkcs10
	 * @throws Exception
	 */
	public static String GenPkcs10(String dn,String pkcs8,
			String hashalg) throws Exception {
		String rv = "";
		try {
			
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64
					.decode(pkcs8));
			ByteArrayInputStream bIn = new ByteArrayInputStream(Base64
					.decode(pkcs8));
			ASN1InputStream dIn = new ASN1InputStream(bIn);;
			ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
			PrivateKeyInfo pri = new PrivateKeyInfo(seq);
			String algid = pri.getAlgorithmId().getAlgorithm().getId();
			String pubkey="";
			String type="RSA";
			if (algid.equals("1.2.840.113549.1.1.1")) // rsa
			{
				KeyFactory keyf = KeyFactory.getInstance("RSA");
				PrivateKey priv = keyf.generatePrivate(priPKCS8);
				RSAPrivateKey prikey=(RSAPrivateKey)priv;
				BigInteger PublicExponent=new BigInteger("65537");
				org.bouncycastle.asn1.pkcs.RSAPublicKey rsa = new org.bouncycastle.asn1.pkcs.RSAPublicKey(
						prikey.getModulus(), PublicExponent);
				pubkey=Base64.toBase64String(rsa.getEncoded());		
			} else if (algid.equals("1.2.840.10045.2.1")) // ecc
			{
				type="SM2";
				KeyFactory keyf = KeyFactory.getInstance("ECDSA");
				PrivateKey priv = keyf.generatePrivate(priPKCS8);
				ECPrivateKey sKey3 = (ECPrivateKey) priv;
				SM2PrivateKeyParsing smpp=new SM2PrivateKeyParsing();
				pubkey=smpp.KeyParsing(sKey3.getS());
			} else {
				throw new Exception("Not supported by the algorithm");
			}
			
			String CertificationRequestInfo=GenCertificationRequestInfo(dn,pubkey,type);
			String signedvalue=SignP1_2(pkcs8,Base64.decode(CertificationRequestInfo),hashalg);
			if(hashalg.toUpperCase().equals("SHA1")||hashalg.toUpperCase().equals("SHA-1"))
			{
				hashalg="1.2.840.113549.1.1.5";
			}
			else if(hashalg.toUpperCase().equals("SHA256")||hashalg.toUpperCase().equals("SHA-256"))
			{
				hashalg="1.2.840.113549.1.1.11";
			}
			else if(hashalg.toUpperCase().equals("SHA512")||hashalg.toUpperCase().equals("SHA-512"))
			{
				hashalg="1.2.840.113549.1.1.13";
			}
			else if(hashalg.toUpperCase().equals("SM3"))
			{
				hashalg="1.2.156.10197.1.501";
			}
			else
			{
				throw new Exception("Not supported by the algorithm");
			}
			
			byte[] cri = Base64.decode(CertificationRequestInfo);
			ASN1Sequence seq2 = DERSequence.getInstance(cri);
			CertificationRequestInfo crio = new CertificationRequestInfo(seq2);
			AlgorithmIdentifier algorithm = null;
			String signalg=hashalg;
			ASN1ObjectIdentifier alg = new ASN1ObjectIdentifier(signalg);
			algorithm = new AlgorithmIdentifier(alg,
					new DERNull());
			DERBitString signature = null;
			signature = new DERBitString(Base64.decode(signedvalue));
			CertificationRequest cr = new CertificationRequest(crio, algorithm,
					signature);
			rv = Base64.toBase64String(cr.getEncoded());
		} catch (Exception e) {
			throw e;
		}
		return rv;
	}
	

	/**
	 * 
	 * @param type 1 为rsa 2为sm2
	 * @param keylen 为rsa时只能为1024及2048 为sm2时只能为256 
	 * @return 证书私钥的pkcs8格式
	 * @throws Exception
	 */
	public static String Genkey(int type, int keylen) throws Exception {
		String rv = "";
		try {
			if (type == 1) {
				Security
						.addProvider(new BouncyCastleProvider());
				KeyPairGenerator generator = KeyPairGenerator.getInstance(
						"RSA", "BC");
				generator.initialize(keylen, new SecureRandom());
				KeyPair pair = generator.generateKeyPair();
				PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(pair
						.getPrivate().getEncoded());
				rv = Base64.toBase64String(priPKCS8.getEncoded());
			} else if (type == 2) {
				Security.addProvider(new BouncyCastleProvider());
				ECPoint kp = null;
				BigInteger k = null;
				SM2 sm2 = SM2.Instance();
				do {
					AsymmetricCipherKeyPair keypair = sm2.ecc_key_pair_generator
							.generateKeyPair();
					ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) keypair
							.getPrivate();
					ECPublicKeyParameters ecpub = (ECPublicKeyParameters) keypair
							.getPublic();
					k = ecpriv.getD();
					kp = ecpub.getQ();
				} while (k.toByteArray().length != 32
						|| kp.getX().getEncoded().length != 32
						|| kp.getY().getEncoded().length != 32
						|| k.toByteArray()[0]==0
						|| kp.getX().getEncoded()[0]==0
						|| kp.getY().getEncoded()[0]==0
				);
				
				if(k.toByteArray()[0]==0||kp.getX().getEncoded()[0]==0||kp.getY().getEncoded()[0]==0)
				{
					//System.out.println("-------------------------------------");
				}
				
				ECCurve curve = null;
				ECPoint ecc_point_g;
				ECFieldElement ecc_gx_fieldelement;
				ECFieldElement ecc_gy_fieldelement;
				BigInteger ecc_p = new BigInteger(sm2_param[0], 16);
				BigInteger ecc_gx = new BigInteger(sm2_param[4], 16);
				BigInteger ecc_gy = new BigInteger(sm2_param[5], 16);
				BigInteger ecc_n = new BigInteger(sm2_param[3], 16);
				BigInteger ecc_a = new BigInteger(sm2_param[1], 16);
				BigInteger ecc_b = new BigInteger(sm2_param[2], 16);
				ecc_gx_fieldelement = new ECFieldElement.Fp(ecc_p, ecc_gx);
				ecc_gy_fieldelement = new ECFieldElement.Fp(ecc_p, ecc_gy);
				curve = new ECCurve.Fp(ecc_p, ecc_a, ecc_b);
				ecc_point_g = new ECPoint.Fp(curve, ecc_gx_fieldelement,
						ecc_gy_fieldelement);
				ECParameterSpec ecSpec = new ECParameterSpec(curve,
						ecc_point_g, // G
						ecc_n); // n
				KeyFactory fact = KeyFactory.getInstance("ECDSA", "BC");
				ECPrivateKey sKey2 = (ECPrivateKey) fact
						.generatePrivate(new ECPrivateKeySpec(k, ecSpec));
				//System.out.println(Hex.toHexString(k.toByteArray()));
				rv = new String(Base64.encode(sKey2.getEncoded()));
			} else {
				throw new Exception("Not supported by the algorithm");
			}
		} catch (Exception e) {
			throw e;
		}
		return rv;
	}
	
	/**
	 * 
	 * @param priInfoByte 证书私钥的pkcs8格式
	 * @param Cert 证书
	 * @param pwd pkcs12的密码
	 * @return pkcs12即pfx
	 * @throws Exception
	 */
	public static String GenPkcs12(String priInfoByte, String Cert, String pwd)
			throws Exception {
		String rv = "";
		try {
			PKCS12KeyStore pkcs12store = new PKCS12KeyStore();
			Key sKey3 = null;
			try {
				sKey3 = sun.security.rsa.RSAPrivateCrtKeyImpl.newKey(Base64
						.decode(priInfoByte));
			} catch (Exception ine) {
				try {
					PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
							Base64.decode(priInfoByte));
					KeyFactory fact = KeyFactory.getInstance("ECDSA", "BC");
					sKey3 = fact.generatePrivate(keySpec);
					// sKey3=new ECPrivateKey();
				} catch (Exception ine2) {
					throw ine2;
				}
			}

			X509CertificateObject[] chain2 = new X509CertificateObject[1];
			byte[] certbyte = Base64.decode(Cert);
			ASN1InputStream dIn;
			ByteArrayInputStream bIn;
			bIn = new ByteArrayInputStream(certbyte);
			dIn = new ASN1InputStream(bIn);
			ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
			Certificate certc = Certificate.getInstance(seq);
			X509CertificateObject clientCert = new X509CertificateObject(certc);
			chain2[0] = clientCert;
			pkcs12store.engineSetKeyEntry("", sKey3, pwd.toCharArray(), chain2);
			java.io.ByteArrayOutputStream stream = new java.io.ByteArrayOutputStream();
			pkcs12store.engineStore(stream, pwd.toCharArray());
			rv = Base64.toBase64String(stream.toByteArray());
		} catch (Exception e) {
			throw e;
		}
		return rv;
	}
	
	
	/**
	 * 
	 * @param d sm2的私钥
	 * @param cert 证书
	 * @param pwd pkcs12的密码
	 * @return pkcs12即pfx
	 * @throws Exception
	 */
	public static String GenP12(String d, String cert, String pwd) throws Exception {
		String rv = "";
		try {
			Security
					.addProvider(new BouncyCastleProvider());
			ECCurve curve = null;
			ECPoint ecc_point_g;
			ECFieldElement ecc_gx_fieldelement;
			ECFieldElement ecc_gy_fieldelement;
			BigInteger ecc_p = new BigInteger(sm2_param[0],
					16);
			BigInteger ecc_gx = new BigInteger(
					sm2_param[4], 16);
			BigInteger ecc_gy = new BigInteger(
					sm2_param[5], 16);
			BigInteger ecc_n = new BigInteger(sm2_param[3],
					16);
			BigInteger ecc_a = new BigInteger(sm2_param[1],
					16);
			BigInteger ecc_b = new BigInteger(sm2_param[2],
					16);
			ecc_gx_fieldelement = new ECFieldElement.Fp(
					ecc_p, ecc_gx);
			ecc_gy_fieldelement = new ECFieldElement.Fp(
					ecc_p, ecc_gy);
			curve = new ECCurve.Fp(ecc_p, ecc_a, ecc_b);
			ecc_point_g = new ECPoint.Fp(curve,
					ecc_gx_fieldelement, ecc_gy_fieldelement);
			ECParameterSpec ecSpec = new ECParameterSpec(
					curve, ecc_point_g, // G
					ecc_n); // n
			KeyFactory fact = KeyFactory.getInstance("ECDSA", "BC");
			BigInteger k = new BigInteger(d, 16);
			ECPrivateKey sKey2 = (ECPrivateKey) fact
					.generatePrivate(new ECPrivateKeySpec(
							k, ecSpec));
			String key = new String(Base64.encode(sKey2.getEncoded()));
			rv = GenPkcs12(key, cert, pwd);
		} catch (Exception ex) {
			throw ex;
		}

		return rv;
	}
	
	/**
	 * 
	 * @param PfxBase64 pkcs12即pfx
	 * @param PfxPwd pkcs12的密码
	 * @param type 类型  1证书 2私钥pkcs8
	 * @return 对应的返回值
	 * @throws Exception
	 */
	public static String ParsingPfx(String PfxBase64, String PfxPwd, int type)
			throws Exception {
		try {
			Security.addProvider(new BouncyCastleProvider());
			PKCS12KeyStore ks = new PKCS12KeyStore();
			ks.engineLoad(new ByteArrayInputStream(Base64.decode(PfxBase64)),
					PfxPwd.toCharArray());
			Enumeration enu = ks.engineAliases();
			String keyAlias = null;
			if (enu.hasMoreElements()) // we are readin just one certificate.
			{
				keyAlias = (String) enu.nextElement();
			}
			Key ecck = ks.engineGetKey(keyAlias, PfxPwd.toCharArray());
			if (ecck.getAlgorithm().toUpperCase().equals("RSA")) {
				X509CertificateObject[] certchain = ks
						.engineGetCertificateChain(keyAlias);
				sun.security.rsa.RSAPrivateCrtKeyImpl sKey3 = (sun.security.rsa.RSAPrivateCrtKeyImpl) ecck;
				if (type == 1) // 证书
				{
					return new String(Base64.encode(certchain[0].getEncoded()));
				} else if (type == 2) // 私钥
				{
					return new String(Base64.encode(sKey3.getEncoded()));
				} else {
					throw new Exception("Invalid parameter");
				}

			} else if (ecck.getAlgorithm().toUpperCase().equals("EC")) {
				X509CertificateObject[] certchain = ks
						.engineGetCertificateChain(keyAlias);
				ECPrivateKey sKey3 = (ECPrivateKey) ecck;
				if (type == 1) // 证书
				{
					return new String(Base64.encode(certchain[0].getEncoded()));
				} else if (type == 2) // 私钥
				{
					return new String(Base64.encode(sKey3.getEncoded()));
					// return sKey3.getS().toString(16).toUpperCase();
				} else {
					throw new Exception("Invalid parameter");
				}
			} else {
				throw new Exception("Invalid alg");
			}

		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param Cert 证书
	 * @param type 类型 1证书主题  2证书序列号 3证书颁发者主题 4证书起始时间 5证书结束时间 6证书密钥用法 7签名算法 8证书扩展  9公钥 10使用者密钥标识符 11颁发者密钥标识符
	 * @param oid 证书的扩展oid名字
	 * @return
	 * @throws Exception
	 */
	public static String ParsingCert(String Cert, int type, String oid)
			throws Exception {
		String rv = "";
		try {
			Security.addProvider(new BouncyCastleProvider());
			ByteArrayInputStream bIn = new ByteArrayInputStream(Base64
					.decode(Cert.getBytes()));
			ASN1InputStream dIn = new ASN1InputStream(bIn);
			ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
			X509CertificateStructure tempcert = new X509CertificateStructure(
					seq);
			SubjectPublicKeyInfo spki = tempcert.getSubjectPublicKeyInfo();
			if (type == 1) {
				rv = rDN(tempcert.getSubject().toString());
			} else if (type == 2) {
				rv = tempcert.getSerialNumber().getPositiveValue().toString(16);
			} else if (type == 3) {
				rv = rDN(tempcert.getIssuer().toString());
			} else if (type == 4) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				rv = sdf.format(tempcert.getStartDate().getDate());
			} else if (type == 5) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				rv = sdf.format(tempcert.getEndDate().getDate());
			} else if (type == 6) {
				X509Extensions exts = tempcert.getTBSCertificate()
						.getExtensions();
				Enumeration enumo = exts.oids();
				while (enumo.hasMoreElements()) {
					ASN1ObjectIdentifier aoid = (ASN1ObjectIdentifier) enumo
							.nextElement();
					if ("2.5.29.15".equals(aoid.getId())) {
						X509Extension ext = exts.getExtension(aoid);
						byte[] extv = ext.getValue().getOctets();

						int CERT_DATA_ENCIPHERMENT_KEY_USAGE = 0x10;
						int CERT_DIGITAL_SIGNATURE_KEY_USAGE = 0x80;
						int CERT_KEY_AGREEMENT_KEY_USAGE = 0x08;
						int CERT_KEY_CERT_SIGN_KEY_USAGE = 0x04;
						int CERT_KEY_ENCIPHERMENT_KEY_USAGE = 0x20;
						int CERT_NON_REPUDIATION_KEY_USAGE = 0x40;
						int CERT_OFFLINE_CRL_SIGN_KEY_USAGE = 0x02;

						byte v = extv[extv.length - 1];
						String rev = "";
						if ((v & CERT_DATA_ENCIPHERMENT_KEY_USAGE) == CERT_DATA_ENCIPHERMENT_KEY_USAGE) //
							rev = rev + ",DATA_ENCIPHERMENT";
						if ((v & CERT_DIGITAL_SIGNATURE_KEY_USAGE) == CERT_DIGITAL_SIGNATURE_KEY_USAGE) //
							rev = rev + ",DIGITAL_SIGNATURE";
						if ((v & CERT_KEY_AGREEMENT_KEY_USAGE) == CERT_KEY_AGREEMENT_KEY_USAGE)
							rev = rev + ",KEY_AGREEMENT";
						if ((v & CERT_KEY_CERT_SIGN_KEY_USAGE) == CERT_KEY_CERT_SIGN_KEY_USAGE)
							rev = rev + ",CERT_SIGN";
						if ((v & CERT_KEY_ENCIPHERMENT_KEY_USAGE) == CERT_KEY_ENCIPHERMENT_KEY_USAGE) //
							rev = rev + ",KEY_ENCIPHERMENT";
						if ((v & CERT_NON_REPUDIATION_KEY_USAGE) == CERT_NON_REPUDIATION_KEY_USAGE) //
							rev = rev + ",NON_REPUDIATION";
						if ((v & CERT_OFFLINE_CRL_SIGN_KEY_USAGE) == CERT_OFFLINE_CRL_SIGN_KEY_USAGE)
							rev = rev + ",OFFLINE_CRL_SIGN";
						if (rev.indexOf(',') != -1) {
							rev = rev.substring(1, rev.length());
						}
						rv = rev;
					}
				}
			} else if (type == 7) {
				rv = tempcert.getSignatureAlgorithm().getAlgorithm().getId();
			} else if (type == 8) {
				X509Extensions exts = tempcert.getTBSCertificate()
						.getExtensions();
				Enumeration enumo = exts.oids();
				while (enumo.hasMoreElements()) {
					ASN1ObjectIdentifier aoid = (ASN1ObjectIdentifier) enumo
							.nextElement();
					if (oid.equals(aoid.getId())) {
						X509Extension ext = exts.getExtension(aoid);
						ASN1Primitive p=ASN1Primitive.fromByteArray(ext.getValue().getOctets());
						DERUTF8String utf8=(DERUTF8String)p;
						rv=utf8.getString();
						break;
					}
				}
			}
			else if(type==9)
			{
				rv=Base64.toBase64String(spki.getPublicKeyData().getBytes());
			}
			else if(type==10) //使用者密钥标识符
			{
				X509Extensions exts = tempcert.getTBSCertificate()
				.getExtensions();
				Enumeration enumo = exts.oids();
				while (enumo.hasMoreElements()) {
					ASN1ObjectIdentifier aoid = (ASN1ObjectIdentifier) enumo
							.nextElement();
					if (aoid.getId().equals("2.5.29.14")) {
						X509Extension ext = exts.getExtension(aoid);
						ASN1Primitive p=ASN1Primitive.fromByteArray(ext.getValue().getOctets());
						DEROctetString dero=(DEROctetString)p;
						rv=Hex.toHexString(dero.getOctets());
						break;
					}
				}
			}
			else if(type==11) //颁发者密钥标识符
			{
				X509Extensions exts = tempcert.getTBSCertificate()
				.getExtensions();
				Enumeration enumo = exts.oids();
				while (enumo.hasMoreElements()) {
					ASN1ObjectIdentifier aoid = (ASN1ObjectIdentifier) enumo
							.nextElement();
					if (aoid.getId().equals("2.5.29.35")) {
						X509Extension ext = exts.getExtension(aoid);
						ASN1Primitive p=ASN1Primitive.fromByteArray(ext.getValue().getOctets());
						DLSequence dlseq=(DLSequence)p;
						DERTaggedObject dt=(DERTaggedObject)dlseq.getObjectAt(0);
						DEROctetString dero=(DEROctetString)dt.getObject();
						rv=Hex.toHexString(dero.getOctets());
						break;
					}
				}
			}
			else {
				throw new Exception("Invalid parameter");
			}
		} catch (Exception e) {
			throw e;
		}
		return rv;
	}
	
	
	
	public static void main(String[] args) {

	}

}
