package serrvice;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private ImageService(){}
    public static ImageService getInstance(){
        return INSTANCE;
    }
    @SneakyThrows
    public  void uploadImage(String fileName, InputStream is){
        String path = "C:\\Java_WorkSpace\\ServFiles\\Library\\books covers\\";
        var fullPath = Path.of(path, fileName);
        try(is){
            Files.write(fullPath,is.readAllBytes());
        }
    }
    @SneakyThrows
    public  Optional<InputStream> loadImageToPage(String fileName){
        String path = "C:\\Java_WorkSpace\\ServFiles\\Library\\books covers\\";
        var file = Path.of(path, fileName);
        return Files.exists(file)
                ?Optional.of(Files.newInputStream(file))
                :Optional.empty();
    }
}
