module michka {
    requires javafx.controls;
    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.google.gson;

    opens Entity to org.hibernate.orm.core;
    opens ApiAdressGouv to com.google.gson;

    exports Graphics;

}