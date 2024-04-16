//author: Mateusz Fydrych :)
//®all rights reserved

package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ImageProcessingApp <input directory> <output directory>");
            return;
        }
        String inputDirectory = args[0];
        String outputDirectory = args[1];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of threads: ");
        int threadsNumber = scanner.nextInt();

        //W files przechowujemy ścieżki do wszystkich plików w folderze wejściowym
        List<Path> files;
        try {
            files = Files.list(Path.of(inputDirectory))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //Tworzymy pulę wątków
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadsNumber);

        long startTime = System.currentTimeMillis();
        //Dla każdego pliku w folderze wejściowym wywołujemy funkcję processImage
        //processImage wczytuje obraz, przetwarza go i zapisuje do folderu wyjściowego
        //forkJoinPool.submit(() -> files.parallelStream().forEach(path -> processImage(path, outputDirectory))).join();
        //dziala to w ten sposob: dla kazdego pliku w folderze wejsciowym wywolujemy funkcje processImage
        //submit powoduje wykonanie funkcji w puli wątków, join powoduje zakończenie programu dopiero po zakończeniu wszystkich wątków
        //map zwraca strumień wyników, które są przetworzone przez funkcję processImage
        //map przetwarza każdy obraz w osobnym wątku
        //foreach zapisuje przetworzone obrazy do folderu wyjściowego
        forkJoinPool.submit(() -> files.parallelStream().map(path -> {
            try {
                BufferedImage originalImage = ImageIO.read(path.toFile());
                return Pair.of(path.getFileName(), originalImage);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).map(pair -> {
            BufferedImage processedImage = imageEdition(pair.getRight());
            return Pair.of(pair.getLeft(), processedImage);
        }).forEach(pair -> {
            try {
                savingImage(pair.getRight(), pair.getLeft(), outputDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        })).join();

        long endTime = System.currentTimeMillis();
        System.out.println("Czas wykonania: " + (endTime - startTime) + " ms");
    }

    private static void processImage(Path path, String outputDirectory) {
        try {
            BufferedImage originalImage = ImageIO.read(path.toFile());
            BufferedImage processedImage = imageEdition(originalImage);
            // Zapis przetworzonego obrazu do pliku
            savingImage(processedImage, path, outputDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage imageEdition(BufferedImage originalImage){
        BufferedImage processedImage = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(),
                originalImage.getType());
        for (int i = 0; i < originalImage.getWidth(); i++) {
            for (int j = 0; j < originalImage.getHeight(); j++) {
                int rgb = originalImage.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                int sumOfColors = (int)(0.299*red + 0.587*blue + 0.114*green);
                Color outColor = new Color(sumOfColors, sumOfColors, sumOfColors);
                int outRgb = outColor.getRGB();
                processedImage.setRGB(i, j, outRgb);
            }
        }
        return processedImage;
    }

    private static void savingImage(BufferedImage processedImage, Path path, String outputDirectory) throws IOException {
        String outputFileName = outputDirectory + "/" + path.getFileName();
        ImageIO.write(processedImage, "jpg", Path.of(outputFileName).toFile());

        System.out.println("Image was handled: " + path.getFileName());
    }
}

//forkJoinPool.submit(() -> files.parallelStream().forEach(path -> processImage(path, outputDirectory))).join();