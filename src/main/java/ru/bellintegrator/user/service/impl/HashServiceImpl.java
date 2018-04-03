package ru.bellintegrator.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.user.dao.UserDAO;
import ru.bellintegrator.user.service.HashService;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class HashServiceImpl implements HashService {

    @Override
    public String getHashByCode(String code) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(code.getBytes(StandardCharsets.UTF_8));
            String s = DatatypeConverter.printHexBinary(hash);
            return s;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

}
