package com.anji_tec.www.netty.protocol;

public class PacketValueDefine {

    public static final class TransmissionQuest {

        public static final class TransportCLS {

            public static final String STORAGE = "1";

            public static final String DIRECT_TRANSFER = "2";
        }
    }

    public static final class DigitalOutput {

        public static final class LampType {

            public static final String DATA_ERROR = "1";

            public static final String PROFILE_ERROR = "2";
        }

        public static final class LampRequest {

            public static final String OFF = "0";

            public static final String ON = "1";

            public static final String BLINK = "2";
        }
    }

    public static final class ModeChangeQuest {

        public static final class ModeChangeCommand {

            public static final String STORAGE_MODE = "1";

            public static final String RETRIEVAL_MODE = "2";
        }
    }

    public static final class ArrivalReport {

        public static final class TransportKey {

            public static final String DUMMY = "99999999";
        }

        public static final class Load {

            public static final String OFF = "0";

            public static final String ON = "1";
        }

        public static final class Profile {

            public static final String LOW = "01";

            public static final String HIGH = "02";
        }
    }

    public static final class TransmissionReply {

        public static final class ResponseCLS {

            public static final String NORMAL = "00";

            public static final String CONDITION_ERROR = "01";

            public static final String BUFFER_FULL = "02";

            public static final String DATA_ERROR = "03";
        }
    }

    public static final class CompleteReply {

        public static final class CompleteCLS {

            public static final String NORMAL = "0";

            public static final String DOUBLE_STORAGE = "1";

            public static final String EMPTY_RETRIEVAL = "2";

            public static final String DISACCORD_PROFILE = "3";
        }
    }

    public static final class RetrievalReply {

        public static final class ResponseCLS {

            public static final String NORMAL = "00";

            public static final String CONDITION_ERROR = "07";

            public static final String BUFFER_FULL = "11";

            public static final String DATA_ERROR = "99";
        }
    }

    public static final class ModeChangeReply {

        public static final class ResponseCLS {

            public static final String NORMAL = "00";

            public static final String MODE_CHANGE = "01";

            public static final String STATION_ERROR = "02";

            public static final String MISSING_MODE = "03";

            public static final String H_MODE = "04";
        }
    }

    public static final class ModeChangeComplete {

        public static final class CompleteMode {

            public static final String STORAGE = "1";

            public static final String RETRIEVAL = "2";
        }
    }

    public static final class MachineStatusReport {

        public static final class DeviceType {

            public static final String SRM = "11";

            public static final String LFT = "22";
        }

        public static final class Status {

            public static final String RUNNING = "0";

            public static final String STOP = "1";

            public static final String ERROR = "2";

            public static final String DISCONNECT = "3";
        }
    }
}
