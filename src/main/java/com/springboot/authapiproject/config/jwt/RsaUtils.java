package com.springboot.authapiproject.config.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;

@Component
public class RsaUtils {

    private static String PUBLIC_KEY_PATH = "src/main/resources/rsa_public.key";
    private static String PRIVATE_KEY_PATH = "src/main/resources/rsa_private.key";

    public void createRsaKeys() {

        Path publicKeyFile = Paths.get(PUBLIC_KEY_PATH);
        Path privateKeyFile = Paths.get(PRIVATE_KEY_PATH);

        if((Files.exists(publicKeyFile) && !Files.isDirectory(publicKeyFile)) &&
                (Files.exists(privateKeyFile) && !Files.isDirectory(privateKeyFile))) {
            return;
        }

        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

//        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        System.out.println(encodedPublicKey);

        try (FileOutputStream fos = new FileOutputStream(PUBLIC_KEY_PATH)) {
            fos.write(publicKey.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(PRIVATE_KEY_PATH)) {
            fos.write(privateKey.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
