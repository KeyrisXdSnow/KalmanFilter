package org.example.model.fileModels;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FileReader {

    public FileReader() {
    }

    public List<FileInfo> readBinFile(String path, int valueSize) throws Exception {

        List<FileInfo> fileInfoList = new ArrayList<>();
        Builder builder = new Builder();
        StringBuilder stringBuilder = new StringBuilder();

        var buffer = Files.readAllBytes(Paths.get(path));

        ExecutorService executor;
        executor = Executors.newFixedThreadPool(13);

        for (int i = 1; i + 12 < buffer.length / valueSize; ) {

            List<Future<Number>> futures = new ArrayList<>();

            for (int j = 0; j < 13; j++) {
                final int finalJ = i * valueSize;
                Future<Number> future;

                switch (j) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 6:
                    case 7:
                    case 8:
                    case 9: {
                        future = executor.submit(
                                () -> getLongValue(Arrays.copyOfRange(buffer, finalJ, finalJ + valueSize))
                        );
                        break;
                    }
                    default: {
                        future = executor.submit(
                                () -> getFloatValue(Arrays.copyOfRange(buffer, finalJ, finalJ + valueSize))
                        );
                    }
                }
                futures.add(future);
                i++;
            }

            var fileInfo = builder
                    .initChannelsAmount((Long) futures.get(0).get())
                    .initSampleSizePerChannel((Long) futures.get(1).get())
                    .initSpectralLinesAmount((Long) futures.get(2).get())
                    .initCutoffFrequency((Long) futures.get(3).get())
                    .initFrequencyResolution((Float) futures.get(4).get())
                    .initTimeReceivingDataBlock((Float) futures.get(5).get())
                    .initTotalDataReceptionTime((Long) futures.get(6).get())
                    .initNumberUserReceivedBlocks((Long) futures.get(7).get())
                    .initDataSize((Long) futures.get(8).get())
                    .initNumberSystemReceivedBlocks((Long) futures.get(9).get())
                    .initMaxReceivedDataValue((Float) futures.get(10).get())
                    .initMinReceivedDataValue((Float) futures.get(11).get())
                    .getFileInfo();

            stringBuilder.append(fileInfo.toString()).append("\n");
            fileInfoList.add(fileInfo);

        }
        executor.shutdown();

        Files.writeString(Paths.get("E:\\Sharaga2.0\\KalmanFilter\\src\\main\\resources\\datafile.txt"), stringBuilder.toString());


        return fileInfoList;
    }

    private long getLongValue(byte[] buffer) {
        reverseArray(buffer);
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        return byteBuffer.getInt();
    }

    private float getFloatValue(byte[] buffer) {
        reverseArray(buffer);
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        return byteBuffer.getFloat();
    }

    private void reverseArray(byte[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            byte temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }


}
