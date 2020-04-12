package com.anji_tec.www.netty.protocol;

public interface FieldLength {

   int LEN_SEQ_NO = 4;

    int LEN_ID = 2;

    int LEN_SEND_TIME = 6;

    int LEN_BCC = 2;

    int LEN_TRANSPORT_KEY = 8;

    int LEN_TRANSPORT_CLS = 1;

    int LEN_STATION_NO = 4;

    int LEN_LOCATION_NO = 12;

    int LEN_LAMP_TYPE = 1;

    int LEN_LAMP_REQUEST = 1;

    int LEN_MODE_CHANGE_COMMAND = 1;

    int LEN_LOAD = 1;

    int LEN_PROFILE = 2;

    int LEN_BC_DATA = 30;

    int LEN_RESPONSE_CLS = 2;

    int LEN_COMPLETE_CLS = 1;

    int LEN_COMPLETE_MODE = 1;

    int LEN_DEVICE_TYPE = 2;

    int LEN_DEVICE_NO = 4;

    int LEN_STATUS = 1;

    int LEN_ERROR_CODE = 7;

    int LEN_MAX_CONTENT = 50;
}
