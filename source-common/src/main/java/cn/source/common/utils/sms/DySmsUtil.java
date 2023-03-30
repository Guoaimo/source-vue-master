package cn.source.common.utils.sms;

import cn.source.common.utils.http.HttpUtils;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 云信短信接口
 * @author: zy
 * @date: 2021年03月30日 11:43
 */
public class DySmsUtil {

    private final static Logger logger= LoggerFactory.getLogger(DySmsUtil.class);

    public static boolean sendSms(String phone, JSONObject paramJson,String accessKeyId
            ,String accessKeySecret,String accessKeytemplate){
        boolean result = true;
        String url = "http://api.sms.cn/sms";
        try {
            String str = HttpUtils.sendPost(url,
                    "ac=send&uid=" +accessKeyId+
                            "&pwd=" +accessKeySecret+
                            "&template=" +accessKeytemplate+
                            "&mobile="+phone+"&content="+paramJson);
            logger.info("短信接口返回的数据----------------"+str);
        }catch (Exception e){
            result = true;
            logger.info("短信接口返回异常----------------"+e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("code", "1234");
        sendSms("18720989281", obj,null,null,null);
    }
}
