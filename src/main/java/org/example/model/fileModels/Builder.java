package org.example.model.fileModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Builder {

    FileInfo fileInfo;

    public Builder() {
        fileInfo = new FileInfo();
    }

    public Builder initChannelsAmount(Long value) {
        this.fileInfo.setChannelsAmount(value);
        return this;
    }

    public Builder initSampleSizePerChannel(Long value) {
        this.fileInfo.setSampleSizePerChannel(value);
        return this;
    }

    public Builder initSpectralLinesAmount(Long value) {
        this.fileInfo.setSpectralLinesAmount(value);
        return this;
    }

    public Builder initCutoffFrequency(Long value) {
        this.fileInfo.setCutoffFrequency(value);
        return this;
    }

    public Builder initFrequencyResolution(float value) {
        this.fileInfo.setFrequencyResolution(value);
        return this;
    }

    public Builder initTimeReceivingDataBlock(float value) {
        this.fileInfo.setTimeReceivingDataBlock(value);
        return this;
    }

    public Builder initTotalDataReceptionTime(Long value) {
        this.fileInfo.setTotalDataReceptionTime(value);
        return this;
    }

    public Builder initNumberUserReceivedBlocks(Long value) {
        this.fileInfo.setNumberUserReceivedBlocks(value);
        return this;
    }

    public Builder initDataSize(Long value) {
        this.fileInfo.setDataSize(value);
        return this;
    }

    public Builder initNumberSystemReceivedBlocks(Long value) {
        this.fileInfo.setNumberSystemReceivedBlocks(value);
        return this;
    }

    public Builder initMaxReceivedDataValue(float value) {
        this.fileInfo.setMaxReceivedDataValue(value);
        return this;
    }

    public Builder initMinReceivedDataValue(float value) {
        this.fileInfo.setMinReceivedDataValue(value);
        return this;
    }

}
