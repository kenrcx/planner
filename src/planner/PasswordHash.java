package planner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ken on 2017/04/08.
 */

//https://gist.github.com/seyan/915057
public class PasswordHash {

    /**
     * ハッシュアルゴリズム
     */
    private static final String ALG = "SHA-256";
    /**
     * ソルト
     */
    private static final String FIXEDSALT = "dsfkgdflksjgkldfsjgkdlsfjgfckljgdfshgjdkf";

    public static String getPasswordHash(com.google.appengine.api.datastore.Key key, String password) {
        return getHash(password, getSalt(key.toString()));
    }

    private static String getHash(String target, String salt){
        return getHash(target + salt);
    }

    private static String getHash(String target) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance(ALG);
            md.update(target.getBytes());
            byte[] digest = md.digest();
            //byte[] -> String
            hash = new String(digest, "UTF-8");
        }catch (NoSuchAlgorithmException e){
            return null;
        }catch (UnsupportedEncodingException e){
            return null;
        }


        return hash;
    }

    private static String getSalt(String userId) {
        return userId + FIXEDSALT;
    }
}

