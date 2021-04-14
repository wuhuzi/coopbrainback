package cn.cocoding.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * AttributeSubEntity
 *
 * @author huzi
 * @date 2021/3/30
 */
@Data
@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AttributeSubEntity {
    private String key;
    private String value;
    private String label;
}
