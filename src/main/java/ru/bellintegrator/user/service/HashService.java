package ru.bellintegrator.user.service;

public interface HashService {



    String getHashByCode(String code);

    Boolean equalHash(String hash1, String hash2);
}
