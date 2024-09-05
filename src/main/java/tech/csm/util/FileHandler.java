package tech.csm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHandler {

	@Value("${file.upload.dir}")
	private String uploadDir;

	public String saveFile(MultipartFile file) throws IOException {
		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("File is empty or null");
		}

		String originalFileName = file.getOriginalFilename();
		if (originalFileName == null || originalFileName.isEmpty()) {
			throw new IllegalArgumentException("Invalid file name");
		}

		// Normalize the file name by replacing spaces with underscores
		String normalizedFileName = originalFileName.replaceAll(" ", "_");

		Path directoryPath = Paths.get(uploadDir);
		if (!Files.exists(directoryPath)) {
			Files.createDirectories(directoryPath); // Ensure the directory exists
		}

		Path filePath = directoryPath.resolve(normalizedFileName);

		// If the file already exists, delete it
		if (Files.exists(filePath)) {
			Files.delete(filePath);
		}

		// Copy the new file to the path
		Files.copy(file.getInputStream(), filePath);

		return normalizedFileName;
	}
}
