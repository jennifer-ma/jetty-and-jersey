import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Species {
    String speciesName;
    int lifeSpan;
    String[] ecosystems;
    Taxonomy taxonomy;
//    boolean isSpeciesNameSet = false;


//    public String getSpeciesName() { return speciesName; }
//    public void setSpeciesName(String speciesName) {
//        isSpeciesNameSet = true;
//        this.speciesName = speciesName;
//    }

    public boolean isMissingMembers() {
        return (speciesName == null ||
                lifeSpan == 0 ||
                ecosystems == null ||
                taxonomy == null) ?
                true : false;
    }
}
