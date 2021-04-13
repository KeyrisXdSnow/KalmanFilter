package org.example.model.fileModels;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class FileInfo implements Serializable {

    private long channelsAmount;
    private long sampleSizePerChannel;
    private long spectralLinesAmount;
    private long cutoffFrequency;
    private float frequencyResolution;
    private float timeReceivingDataBlock;
    private long totalDataReceptionTime;
    private long numberUserReceivedBlocks;
    private long dataSize;
    private long numberSystemReceivedBlocks;
    private float maxReceivedDataValue;
    private float minReceivedDataValue;

}
