package ru;

import java.util.NoSuchElementException;

public class FileNotFoundInStorageException extends NoSuchElementException {

    public FileNotFoundInStorageException(String message)
    {
            super(message);
    }
}

