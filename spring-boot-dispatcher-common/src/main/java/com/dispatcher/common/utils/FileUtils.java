/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dispatcher.common.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    //list files from a directory
    public static Set<String> listFiles(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    //Split large file
    public static List<File> splitByFileSize(File largeFile, int maxSizeOfSplitFiles, String splitFileDirPath) throws IOException {
        List<File> listOfSplitFiles = new ArrayList<>();
        try (InputStream in = Files.newInputStream(largeFile.toPath())) {
            final byte[] buffer = new byte[maxSizeOfSplitFiles];
            int dataRead = in.read(buffer);
            while (dataRead > -1) {
                File splitFile = getSplitFile(FilenameUtils.removeExtension(largeFile.getName()),
                        buffer, dataRead, splitFileDirPath);
                listOfSplitFiles.add(splitFile);
                dataRead = in.read(buffer);
            }
        }
        return listOfSplitFiles;
    }

    private static File getSplitFile(String largeFileName, byte[] buffer, int length, String splitFileDirPath) throws IOException {
        File splitFile = File.createTempFile(largeFileName + "-", "-split", new File(splitFileDirPath));
        try (FileOutputStream fos = new FileOutputStream(splitFile)) {
            fos.write(buffer, 0, length);
        }
        return splitFile;
    }
}
