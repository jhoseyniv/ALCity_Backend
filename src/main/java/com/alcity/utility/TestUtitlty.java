package com.alcity.utility;

import com.alcity.dto.Interpreter.PLData;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Locale;

public class TestUtitlty {

    public static void main(String args[]) throws IOException, JSONException {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("uuuu-MM-dd 'Time' HH:mm:ssXXX")
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']')
                .toFormatter(Locale.ENGLISH);

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdtNow = ZonedDateTime.now(zoneId);
        System.out.println(zdtNow);
        long epochSecond = zdtNow.toEpochSecond();
        System.out.println(epochSecond);
        ZonedDateTime zdtFromEpochSeconds = Instant.ofEpochSecond(epochSecond).atZone(zoneId);
        System.out.println(zdtFromEpochSeconds.format(formatter));

        StringBuffer sbf = new StringBuffer("{\n" +
                "  \"settings\": {\n" +
                "    \"skyboxId\": \"dreamy_sky\",\n" +
                "    \"environmentId\": \"Rural_Area\",\n" +
                "    \"startPosition\": {\n" +
                "      \"x\": 2,\n" +
                "      \"y\": 0,\n" +
                "      \"z\": 3\n" +
                "    }\n" +
                "  },\n" +
                "  \"rows\": [    \n" +
                "      [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"tileObjId\": \"Grass\",\n" +
                "          \"alpha\": 1,\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"alpha\": 0.5,\n" +
                "          \"tileObjId\": \"Water\",\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 3,\n" +
                "          \"tileObjId\": \"Grass\",\n" +
                "          \"alpha\": 1,\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "\t  [\n" +
                "        {\n" +
                "          \"id\": 4,\n" +
                "          \"tileObjId\": \"Grass\",\n" +
                "          \"alpha\": 1,\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 5,\n" +
                "          \"alpha\": 0.5,\n" +
                "          \"tileObjId\": \"Water\",\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 6,\n" +
                "          \"tileObjId\": \"Grass\",\n" +
                "          \"alpha\": 1,\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "\t  [\n" +
                "        {\n" +
                "          \"id\": 7,\n" +
                "          \"tileObjId\": \"Grass\",\n" +
                "          \"alpha\": 1,\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 8,\n" +
                "          \"alpha\": 0.5,\n" +
                "          \"tileObjId\": \"Water\",\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 9,\n" +
                "          \"tileObjId\": \"Grass\",\n" +
                "          \"alpha\": 1,\n" +
                "          \"zPosition\": 0,\n" +
                "          \"margin\": {\n" +
                "            \"top\": 0,\n" +
                "            \"bottom\": 0,\n" +
                "            \"left\": 0,\n" +
                "            \"right\": 0\n" +
                "          }\n" +
                "        }\n" +
                "      ]    \n" +
                "  ]\n" +
                "}");
        byte[] bytes = sbf.toString().getBytes();
        System.out.println(Arrays.toString(bytes));
        String s = new String(bytes, StandardCharsets.US_ASCII);
        JSONObject objJsonObject = new JSONObject(s);

     //   StringBuffer buf = new StringBuffer( bytes.toString() );
       // StringBuffer decoded = new StringBuffer(bytes, "UTF-8");
      //  StringBuilder decoded =new StringBuilder(Charset.forName("UTF-8").decode(ByteBuffer.wrap(bytes)));

        System.out.println(objJsonObject.toString());

    }
}
