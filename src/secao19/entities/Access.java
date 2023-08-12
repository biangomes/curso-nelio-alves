package secao19.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Access {
    private String name;
    private String dtAccess;
    private static DateFormat dateFormatISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public Access(){}

    public Access(String name, String dtAccess) {
        this.name = name;
        this.dtAccess = dtAccess;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDtAccess(String dtAccess) throws ParseException {
        Date dtAccessConverted = dateFormatISO.parse(dtAccess);
        this.dtAccess = String.valueOf(dtAccessConverted);
    }

    public String getName() {
        return name;
    }

    public String getDtAccess() {
        return dtAccess;
    }

    public static DateFormat getDateFormatISO() {
        return dateFormatISO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Access access = (Access) o;

        if (!name.equals(access.name)) return false;
        return dtAccess.equals(access.dtAccess);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dtAccess.hashCode();
        return result;
    }
}
