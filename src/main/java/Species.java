import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Species {
    String speciesName;
    int lifeSpan;
    String[] ecosystems;
    Taxonomy taxonomy;


//    public String getSpeciesName() { return speciesName; }
//    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }
}
