package org.example.prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SqlFileReader {
    public static String readSqlFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
