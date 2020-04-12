package com.anji_tec.www.netty.protocol.command;

public interface Command {

    byte LOGIN_REQUEST = 1;

    byte LOGIN_RESPONSE = 2;

    byte LOGOUT_REQUEST = 3;

    byte LOGOUT_RESPONSE = 4;

    byte SEND_TO_USER_REQUEST = 5;

    byte SEND_TO_USER_RESPONSE = 6;

    byte CREATE_GROUP_REQUEST = 7;

    byte CREATE_GROUP_RESPONSE = 8;

    byte JOIN_GROUP_REQUEST = 9;

    byte JOIN_GROUP_RESPONSE = 10;

    byte QUIT_GROUP_REQUEST = 11;

    byte QUIT_GROUP_RESPONSE = 12;

    byte LIST_GROUP_MEMBERS_REQUEST = 13;

    byte LIST_GROUP_MEMBERS_RESPONSE = 14;

    byte SEND_TO_GROUP_REQUEST = 15;

    byte SEND_TO_GROUP_RESPONSE = 16;

    byte HEART_BEAT_REQUEST = 17;

    byte HEART_BEAT_RESPONSE = 18;
}
