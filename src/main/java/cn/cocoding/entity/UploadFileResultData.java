package cn.cocoding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-27
 *
 */
@Data
@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UploadFileResultData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;

    private String fileUrl;

    @Override
    public String toString() {
        return "{" +
                "fileName:'" + fileName + '\'' +
                ", fileUrl:'" + fileUrl + '\'' +
                '}';
    }
}
