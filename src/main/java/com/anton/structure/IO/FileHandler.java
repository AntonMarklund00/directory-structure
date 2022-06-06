package com.anton.structure.IO;


import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {

    public void structureDir(String path) {
        List<String> files = getFilesInDirectory(path);
        path = path + (path.endsWith("/")?"":"/");
        String finalPath = path;

        files.forEach(file -> {
            String filePath = finalPath + file;
            if (isNotDirectory(filePath)){
                String fileType = getFileType(filePath);
                if (isNotDirectory(finalPath + fileType)){
                    createDirectory(finalPath+fileType);
                }
                moveFile(filePath, finalPath+fileType+"/"+file);
            }else{
                if (isNotDirectory(finalPath + "directories")){
                    createDirectory(finalPath+"directories");
                }
                moveFile(filePath, finalPath+"directories/"+file);
            }
        });
    }

    private List<String> getFilesInDirectory(String path){
        return Stream.of(Objects.requireNonNull(new File(path).listFiles()))
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private String getFileType(String filePath){
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    private boolean isNotDirectory(String filePath){
        return !new File(filePath).isDirectory();
    }

    private void createDirectory(String filePath){
        new File(filePath).mkdir();
    }

    private void moveFile(String source, String destination){
        new File(source).renameTo(new File(destination));
    }
}
