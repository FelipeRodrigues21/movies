package com.raspberryward.raspberryward.Dto;

import java.io.Serializable;
import java.util.List;

public class AwardMaxMinDto implements Serializable {

    private List<AwardDto> max;

    private List<AwardDto> min;

    public List<AwardDto> getMax() {
        return max;
    }

    public void setMax(List<AwardDto> max) {
        this.max = max;
    }

    public List<AwardDto> getMin() {
        return min;
    }

    public void setMin(List<AwardDto> min) {
        this.min = min;
    }
}
