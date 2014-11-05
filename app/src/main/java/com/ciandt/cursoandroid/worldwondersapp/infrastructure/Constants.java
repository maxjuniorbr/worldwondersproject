package com.ciandt.cursoandroid.worldwondersapp.infrastructure;

public class Constants {

    public static interface Entity {

        public static interface User {

            public static final String TEXT_COUNTRY_BRASIL = "Brasil";
            public static final String TEXT_COUNTRY_CANADA = "Canadá";
            public static final String TEXT_COUNTRY_CHINA = "China";
            public static final String INDICATOR_GENDER_MALE = "M";
            public static final String INDICATOR_GENDER_FEMALE = "F";
            public static final int INDICATOR_NOTIFICATION_ON = 1;
            public static final int INDICATOR_NOTIFICATION_OFF = 0;
        }
    }

    public static interface Bundle {

        public static final String BUNDLE_USER_NAME = "user_name";
        public static final String BUNDLE_USER_EMAIL = "user_email";
        public static final String BUNDLE_USER_PASSWORD = "user_password";
    }

    public static interface RequestCode {

        public static final int MAIN_ACTIVITY = 0;
        public static final int LOGIN_ACTIVITY = 1;
        public static final int REGISTER_ACTIVITY = 2;
    }

}