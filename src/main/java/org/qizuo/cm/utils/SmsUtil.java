package org.qizuo.cm.utils;

import com.jasson.mas.api.ApiException;
import com.jasson.mas.api.common.ConnectStatus;
import com.jasson.mas.api.sms.SmsApiClient;
import com.jasson.mas.api.sms.SmsApiClientHandler;
import com.jasson.mas.api.sms.SmsApiClientImpl;
import com.jasson.mas.api.smsapi.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: fangl
 * @description: 短信类
 * @date: 9:10 2019/2/12
 */
public class SmsUtil {
    /** ***************************方式一：长连接通道*************************** */
    /**
     * 基本参数
     */
    //API插件标识
    private static final String appID = "API插件";
    //API插件密码
    private static final String appPwd = "1231234";
    //短信扩展码
    private static final String xcode = "22223";
    //mas端口
    private static final int masApiPort = 61616;
    //masurl
    private static final String masIP = "192.168.1.2219";
    //发送对象
    private static SmsApiClient smsApiClient = null;


    /**
     * @author: fangl
     * @description: 建立连接
     * @date: 11:18 2019/2/27
     */
    public static boolean connect() {
        try {
            //创建一个过滤器(因为静态方法中不能直接new一个内部类，只能用这种方法)
            SmsApiClientHandler smsHandler = new SmsUtil().new SmsApiClientHandlerImpl();
            //建立连接
            smsApiClient = new SmsApiClientImpl(smsHandler, masIP, masApiPort, appID, appPwd);

            //设置是否自动重连到服务器(可以不需要设置)
            smsApiClient.setAutoConnect(true);

            //设置自动重连服务器相隔时间(单位：秒), 默认为30秒(可以不需要设置)
            smsApiClient.setReConnectInterval(60);

            //设置与服务连接超时时长，单位：millisecond(可以不需要设置)
            smsApiClient.setConnectTimeout(100000);

            //设置发送超时时长，单位：millisecond(可以不需要设置)
            smsApiClient.setSendTimeout(1000000);

            //建立连接
            smsApiClient.start();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @author: fangl
     * @description: 连接判断
     * @date: 11:22 2019/2/27
     */
    public static boolean sendSmBefore(int mobiles) {
        try {
            //初次连接
            if (null == smsApiClient) {
                connect();
            }
            //判断是否连接，获取网关连接状态(Connect：连接正常, Disconnect：断连, NotConnect：没有连 接, Other：其他)
            ConnectStatus connectStatus = smsApiClient.getConnStatusIAGW();
            if (!ConnectStatus.Connect.equals(connectStatus)) {
                System.out.println("网关状态****" + connectStatus);
                //先关闭再连接
                //smsApiClient.loginOut();
                smsApiClient.close();
                smsApiClient.start();
                return false;
            }
            //短信数量限制
            int ret = smsApiClient.getDestAddrsLimit();
            if (mobiles > ret) {
                return false;
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @author: fangl
     * @description: 发送对象封装
     * @date: 11:26 2019/2/27
     */
    public static SmsSendRequest sendSmRequest() {
        SmsSendRequest smsSendRequest = new SmsSendRequest();
        // 短信存活期，单位秒
        smsSendRequest.validTime = 10000;
        //短信扩展码
        smsSendRequest.xCode = xcode;
        //短信编码类型
        smsSendRequest.msgFormat = MsgFmt.GB2312;
        //短信是否需要状态报告
        smsSendRequest.isNeedReport = true;
        //短信网关优先级, 短信优先级大于0 的整数 0为最高优先级， 数字越大级别越低
        smsSendRequest.priority = 1;
        // Normal: 普通短信,Instant：免提短信, Long：长短信,Structured：二进制短信,WapPush： //WapPush 短信
        smsSendRequest.type = SmsType.Normal;
        smsSendRequest.appID = appID;
        return smsSendRequest;
    }

    /**
     * @author: fangl
     * @description: 发送短信
     * @date: 11:04 2019/2/27
     */
    public static boolean sendSm(List<String> mobiles, String content) {
        try {
            //发送前连接判断
            if (!sendSmBefore(mobiles.size())) {
                return false;
            }

            // 构建发射体
            SmsSendRequest smsSendRequest = sendSmRequest();

            //短信地址装载
            smsSendRequest.destAddrs = mobiles;

            //短信内容装载
            smsSendRequest.message = content;

            //发送
            SmsSendResponse smsSendResponse = smsApiClient.sendSms(smsSendRequest);

            /*//计算短信条数和字数
            SmsCount smsCount= smsApiClient. getSmsCount(content, MsgFmt.GB2312, SmsType.Normal);
            //获得扩展服务代码(插件短信扩展号码+流水号)长度
            int xcodeLength= smsApiClient.getXCodeLength();*/
        } catch (Exception e) {
            System.out.println("API短信客户端调用失败:" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @author: fangl
     * @description: 重写短信发送过滤器
     * @date: 11:08 2019/2/27
     */
    public class SmsApiClientHandlerImpl implements SmsApiClientHandler {
        /**
         * 接收状态报告
         */
        @Override
        public void notifySmsDeliveryStatus(String submitID, Report[] report) {
            System.out.println("接收到状态报告"
                    + report.length + ": SmsReport submitID: "
                    + submitID + " sendResul= "
                    + report[0].sendResult);
        }

        /**
         * 接收MO短信
         */
        @Override
        public void notifySmsReception(Sms sms) {
            System.out.println("收到一条MO，destId："
                    + sms.destID + "；内容：" + sms.content + "手机号码：" + sms.mobile);
        }
    }

    /** ***************************方式二：webservice*************************** */
    /**
     * 插件标识
     */
    private static final String WEBSERVICEAID = "KeJiDeSvelop_SSMSERES";
    /**
     * webservice访问地址,内网
     */
    private static final String WEBSERVICEURL = "http://192.168.12.34232:8191/services/cmcc_mas_wbs";

    /**
     * @author: fangl
     * @description: 调用
     * @date: 9:59 2019/1/3
     */
    public static String sendSms(List<String> phones, String content) throws Exception {
        //发送短信
        String meString = getSoapInputStream(phones, content);
        return meString;
    }

    /**
     * @author: fangl
     * @description: http
     * @date: 9:58 2019/1/3
     */
    private static String getSoapInputStream(List<String> phones, String content) throws Exception {
        //拼接请求报文
        String sendMsg = getSoapRequest(phones, content);
        String respContent = "";
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost request2 = new HttpPost(WEBSERVICEURL);
            request2.setEntity(new StringEntity(sendMsg, "utf-8"));
            request2.setHeader("Content-Type", "application/soap+xml; charset=utf-8");
            request2.setHeader("SOAPAction", "http://url:port/service/sendSms");
            HttpResponse response2 = client.execute(request2);
            HttpEntity entity = response2.getEntity();
            if (entity != null) {
                respContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            throw e;
        }
        return respContent;
    }

    /**
     * @author: fangl
     * @description: 拼接
     * @date: 9:58 2019/1/3
     */
    private static String getSoapRequest(List<String> phones, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sms=\"http://www.csapi.org/schema/sms\">\n");
        sb.append("	<soapenv:Header/>\n");
        sb.append("	<soapenv:Body>\n");

        sb.append("		<sms:sendSmsRequest>\n");
        sb.append("			<ApplicationID>" + WEBSERVICEAID + "</ApplicationID>\n");
        sb.append("			<!--Zero or more repetitions:-->\n");
        for (String phone : phones) {
            sb.append("			<DestinationAddresses>tel:" + phone + "</DestinationAddresses>\n");
        }
        sb.append("			<ExtendCode>1</ExtendCode>\n");
        sb.append("			<Message>" + content + "</Message>\n");
        sb.append("			<MessageFormat>GB2312</MessageFormat>\n");
        sb.append("			<SendMethod>Normal</SendMethod>\n");
        sb.append("			<DeliveryResultRequest>True</DeliveryResultRequest>\n");
        sb.append("		</sms:sendSmsRequest>\n");

        sb.append("	</soapenv:Body>\n");
        sb.append("</soapenv:Envelope>");
        return sb.toString();
    }

    /** ***************************方式三：jdbc插入数据库*************************** */
    /**
     * 基本参数配置
     */
    private final static String JDBCDRIVER = "com.mysql.jdbc.Driver";
    private final static String JDBCURL = "jdbc:mysql://192.168.70.18:3306/massi";
    private final static String JDBCUSERNAME = "root";
    private final static String JDBCPASSWORD = "rootcmcc2017";
    //安全token
    private final static String JDBCAPPLICATIONID = "p201810241220233";
    //连接对象
    private static Connection conn = null;

    /**
     * 创建连接
     *
     * @return
     * @author fangl
     * 2018-10-22 下午4:06:26
     */
    private static Connection getConn() {
        try {
            Class.forName(JDBCDRIVER);
            conn = DriverManager.getConnection(JDBCURL, JDBCUSERNAME, JDBCPASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 插入短信信息
     *
     * @return
     * @author fangl
     * 2018-10-22 下午4:06:55
     */
    private static int iMessage(String number, String content) {
        Connection conn = getConn();
        int i = 0;
        if (null != conn) {
            //语句拼接
            String sql = "insert into sms_outbox (SISMSID,EXTCODE,DESTADDR,MESSAGECONTENT,REQDELIVERYREPORT,MSGFMT,SENDMETHOD,REQUESTTIME,APPLICATIONID) values" +
                    "('" + UUID.randomUUID().toString() + "','',?,?,0,0,0,now(),'" + JDBCAPPLICATIONID + "')";
            //通过连接获取执行器
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(sql);
                //塞入数值
                //手机号码
                pstmt.setString(1, number);
                //短信内容
                pstmt.setString(2, content);
                //执行插入
                i = pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //释放拦截
                relaseConn(pstmt, conn, null);
            }
        }
        return i;
    }

    /**
     * 释放连接
     *
     * @param preparedStatement
     * @param conn
     * @return
     * @author fangl
     * 2018-10-22 下午4:56:38
     */
    private static boolean relaseConn(PreparedStatement preparedStatement, Connection conn, ResultSet resultSet) {
        try {
            if (null != preparedStatement) {
                preparedStatement.close();
            }
            if (null != conn) {
                conn.close();
            }
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 发送短信
     *
     * @param number
     * @param content
     * @return
     * @author fangl
     * 2018-10-22 下午4:07:02
     */
    public static long sendSms(String number, String content) {
        return iMessage(number, content);
    }
}
