package com.cqzx.comm.util.cert;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.TBSCertificateStructure;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
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

import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;


public class SM2PrivateKeyParsing {
	
	
	
	public static String[] sm2_param = {
		"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF",// p,0
		"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC",// a,1
		"28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93",// b,2
		"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123",// n,3
		"32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7",// gx,4
		"BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0" // gy,5
	};
	
	public static String[] ecc_param = sm2_param;
	
	
	public static BigInteger ecc_p;
	public static BigInteger ecc_a;
	public static BigInteger ecc_b;
	public static BigInteger ecc_n;
	public static BigInteger ecc_gx;
	public static BigInteger ecc_gy;

	public static ECCurve ecc_curve;
	public static ECPoint ecc_point_g;
	
	public static ECDomainParameters ecc_bc_spec;
	public static ECKeyPairGenerator ecc_key_pair_generator;
	
	static
	{
		ecc_param = sm2_param;
		ECFieldElement ecc_gx_fieldelement;
		ECFieldElement ecc_gy_fieldelement;

		ecc_p = new BigInteger(ecc_param[0], 16);
		ecc_a = new BigInteger(ecc_param[1], 16);
		ecc_b = new BigInteger(ecc_param[2], 16);
		ecc_n = new BigInteger(ecc_param[3], 16);
		ecc_gx = new BigInteger(ecc_param[4], 16);
		ecc_gy = new BigInteger(ecc_param[5], 16);

		ecc_gx_fieldelement = new ECFieldElement.Fp(ecc_p, ecc_gx);
		ecc_gy_fieldelement = new ECFieldElement.Fp(ecc_p, ecc_gy);

		ecc_curve = new ECCurve.Fp(ecc_p, ecc_a, ecc_b);
		ecc_point_g = new ECPoint.Fp(ecc_curve, ecc_gx_fieldelement,
				ecc_gy_fieldelement);

		ecc_bc_spec = new ECDomainParameters(ecc_curve, ecc_point_g, ecc_n);

		ECKeyGenerationParameters ecc_ecgenparam;
		
		SecureRandom secRand=new SecureRandom();
		ecc_ecgenparam = new ECKeyGenerationParameters(ecc_bc_spec,secRand);
		ecc_key_pair_generator = new ECKeyPairGenerator();
		ecc_key_pair_generator.init(ecc_ecgenparam);
	}
	

	public String KeyParsing(BigInteger dd) throws Exception
	{
		String rv="";
		try
		{
			ECPoint kp = null;
			BigInteger k = dd;
			kp=ecc_point_g.multiply(k);
			byte[] xy = new byte[64];
			for (int i = 0; i < 32; i++) {
				xy[i] = kp.getX().getEncoded()[i];
				xy[i + 32] = kp.getY().getEncoded()[i];
			}
			rv = Base64.toBase64String(xy);
		}catch(Exception e)
		{
			throw e;
		}
		return rv;
		
	}
	
	
	
	
	
	    
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
	}

}
