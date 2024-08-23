package com.xczx.base.exception;

/**
 * @description 学成在线项目异常类
 * @author cyhjava
 * @date 2022/9/6 11:29
 * @version 1.0
 */
public class XCZXException extends RuntimeException {

   private String errMessage;

   public XCZXException() {
      super();
   }

   public XCZXException(String errMessage) {
      super(errMessage);
      this.errMessage = errMessage;
   }

   public String getErrMessage() {
      return errMessage;
   }

   public static void cast(CommonError commonError){
       throw new XCZXException(commonError.getErrMessage());
   }
   public static void cast(String errMessage){
       throw new XCZXException(errMessage);
   }

}
