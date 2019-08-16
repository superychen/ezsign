package com.cqzx.comm.util.cert;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.ECKey;
import java.util.Date;

import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

@SuppressWarnings("deprecation")
public class SM2 {

	public boolean sm2Test = false;

	public String[] ecc_param = sm2_test_param;

	public BigInteger ecc_p;
	public BigInteger ecc_a;
	public BigInteger ecc_b;
	public BigInteger ecc_n;
	public BigInteger ecc_gx;
	public BigInteger ecc_gy;

	public ECCurve ecc_curve;
	public ECPoint ecc_point_g;

	 //ecc 
	
	public static String[] sm2_test_param = {
			"8542D69E4C044F18E8B92435BF6FF7DE457283915C45517D722EDB8B08F1DFC3",// p,0
			"787968B4FA32C3FD2417842E73BBFEFF2F3C848B6831D7E0EC65228B3937E498",// a,1
			"63E4C6D3B23B0C849CF84241484BFE48F61D59A5B16BA06E6E12D1DA27C5249A",// b,2
			"8542D69E4C044F18E8B92435BF6FF7DD297720630485628D5AE74EE7C32E79B7",// n,3
			"421DEBD61B62EAB6746434EBC3CC315E32220B3BADD50BDC4C4E6C147FEDD43D",// gx,4
			"0680512BCBB42C07D47349D2153B70C4E5D7FDFCBFA36EA1A85841B9E46E09A2" // gy,5
	};

	public static String[] sm2_param = {
			"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF",// p,0
			"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC",// a,1
			"28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93",// b,2
			"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123",// n,3
			"32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7",// gx,4
			"BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0" // gy,5
	};

	public ECDomainParameters ecc_bc_spec;

	public ECKeyPairGenerator ecc_key_pair_generator;

	private SM2(boolean sm2Test) {
		this.sm2Test = sm2Test;

		if (sm2Test)
			ecc_param = sm2_test_param;
		else
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
		//System.out.println(secRand);
		ecc_ecgenparam = new ECKeyGenerationParameters(ecc_bc_spec,secRand);
		ecc_key_pair_generator = new ECKeyPairGenerator();
		ecc_key_pair_generator.init(ecc_ecgenparam);

	}

	public static SM2 Instance() {
		return new SM2(false);
	}

	public static SM2 InstanceTest() {
		return new SM2(true);
	}
	
	public byte[] eraseF0 (byte[] bb)
	{
		if(bb.length==32)
    	{
    		return bb;
    	}
		if(bb[0]==0)
		{
			byte []cc=new byte[32];
			System.arraycopy(bb,1, cc, 0, 32);
			return cc;
		}
		else
		{
			return bb;
		}
	}

	public byte[] Sm2GetZ(byte[] userId, ECPoint userKey) {
		Security.addProvider(new BouncyCastleProvider());
		Digest sm3 = null;
		sm3=new SM3Digest(); 
		
		byte[] p;
		// userId length
		int len = userId.length * 8;
		sm3.update((byte) (len >> 8 & 0x00ff));
		sm3.update((byte) (len & 0x00ff));
		// userId
		sm3.update(userId, 0, userId.length);
		// a,b
		p = ecc_a.toByteArray();
		sm3.update(eraseF0(p), 0,32);
		p = ecc_b.toByteArray();
		sm3.update(eraseF0(p), 0, 32);
		// gx,gy
		p = ecc_gx.toByteArray();
		sm3.update(eraseF0(p), 0, 32);
		p = ecc_gy.toByteArray();
		sm3.update(eraseF0(p), 0, 32);
		// x,y
		p = userKey.getX().toBigInteger().toByteArray();
		sm3.update(eraseF0(p), 0, 32);
		p = userKey.getY().toBigInteger().toByteArray();
		
		sm3.update(eraseF0(p), 0, 32);
		// Z
		byte[] md = new byte[sm3.getDigestSize()];
		sm3.doFinal(md, 0);
		return md;
	}

	public void Sm2Sign(byte[] md, BigInteger userD, ECPoint userKey,
			SM2Result sm2Ret) {
		// e
		BigInteger e = new BigInteger(1, md);
		// k
		BigInteger k = null;
		ECPoint kp = null;
		BigInteger r = null;
		BigInteger s = null;

		do {
			do {
				if (!sm2Test) {
					AsymmetricCipherKeyPair keypair = ecc_key_pair_generator
							.generateKeyPair();
					ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) keypair
							.getPrivate();
					ECPublicKeyParameters ecpub = (ECPublicKeyParameters) keypair
							.getPublic();
					k = ecpriv.getD();
					kp = ecpub.getQ();
					
				} else {
					String kS = "6CB28D99385C175C94F94E934817663FC176D925DD72B727260DBAAE1FB2F96F";
					k = new BigInteger(kS, 16);
					kp = ecc_point_g.multiply(k);
				}
					
				// r
				r = e.add(kp.getX().toBigInteger());
				r = r.mod(ecc_n);
			} while (r.equals(BigInteger.ZERO) || r.add(k).equals(ecc_n)||(r.toByteArray().length!=32&&r.toByteArray().length!=33)||(r.toByteArray().length==32&&r.toByteArray()[0]==0));
			
			// (1 + dA)~-1
			BigInteger da_1 = userD.add(BigInteger.ONE);
			da_1 = da_1.modInverse(ecc_n);
			// s
			s = r.multiply(userD);
			s = k.subtract(s).mod(ecc_n);
			s = da_1.multiply(s).mod(ecc_n);
			
		} while (s.equals(BigInteger.ZERO)||(s.toByteArray().length!=32&&s.toByteArray().length!=33)||(s.toByteArray().length==32&&s.toByteArray()[0]==0));
		
		sm2Ret.r = r;
		sm2Ret.s = s;
	}

	public void Sm2Verify(byte[] md, ECPoint userKey, BigInteger r,
			BigInteger s, SM2Result sm2Ret) {
		sm2Ret.R = null;

		// e_
		BigInteger e = new BigInteger(1, md);
		// t
		BigInteger t = r.add(s).mod(ecc_n);

		if (t.equals(BigInteger.ZERO))
			return;

		// x1y1
		ECPoint x1y1 = ecc_point_g.multiply(sm2Ret.s);
		x1y1 = x1y1.add(userKey.multiply(t));

		// R
		sm2Ret.R = e.add(x1y1.getX().toBigInteger()).mod(ecc_n);
	}

	
	  public static ECPoint generationECPointPublickey(String x,String y)
		{
			ECPoint unCompP=null;
			try
			{
				BigInteger q= new BigInteger(
						"115792089210356248756420345214020892766250353991924191454421193933289684991999",
						10);
				BigInteger a= new BigInteger(
						"1111111111111111111111111111111011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111100",
						2);
				BigInteger b= new BigInteger(
						"10100011101001111110101001111010011101100111110101111000110100010011010101101010011110010010111100111101100101000010011010011111110011100101111000100111110101000101011010101110001111100100101101110110111100101111010100000101001101100101000000111010010011",
						2);
				BigInteger x1= new BigInteger(   //��Կ
						x,
						16);
				//String res_2 = x1.toString(2);
				BigInteger y1= new BigInteger(  //��Կ
						y,
						16);
				ECFieldElement x2=new ECFieldElement.Fp(q,x1);
				ECFieldElement y2=new ECFieldElement.Fp(q,y1);
				ECCurve cu=new ECCurve.Fp(q,a,b);
				unCompP = new ECPoint.Fp(cu, x2,y2,false);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return unCompP;
		}
		
		
		public static String bytesToHexString(byte[] src){   
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
		
	public static byte[] bytesReverseOrder(byte[] b) {
		int length = b.length;
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[length - i - 1] = b[i];
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
