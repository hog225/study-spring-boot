package org.yg.study.JPAsample.manytomany.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GolferGearEntityId implements Serializable {
    private GolferEntity golfer;
    private GearEntity gear;

}
