import java.io.Serializable;

public class AppleProduct implements Serializable {
    // sebagai inisial ID ketika proses Deserialize (Object to String/ extract) dari Serialize (String to object/ Object)
    // The serialVersionUID attribute is an identifier that is used to serialize/deserialize an object of a Serializable class.
    // use the serialVersionUID attribute to remember versions of a Serializable class to verify that a loaded class and the serialized object are compatible.
    private static final long serialVersionUID = 7172774663214511766L; // try change another number, then Deserialize will error "...local class incompatible: stream classdesc serialVersionUID=..."
    public String headphonePort;
    public String thunderboltPort;
    public String lightningPort;

    public String getHeadphonePort() {
        return headphonePort;
    }

    public String getThunderboltPort() {
        return thunderboltPort;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLightningPort() {
        return lightningPort;
    }
}