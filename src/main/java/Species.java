import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Species {
    String speciesName;
    int lifeSpan;
    String[] ecosystems;
    Taxonomy taxonomy;

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }
    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String[] getEcosystems() {
        return ecosystems;
    }
    public void setEcosystems(String[] ecosystems) {
        this.ecosystems = ecosystems;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }
    public void setTaxonomy(Taxonomy taxonomy) {
        this.taxonomy = taxonomy;
    }


    public boolean checkMissingMembers() {
        return (speciesName == null ||
                lifeSpan == 0 ||
                ecosystems == null ||
                taxonomy == null);
    }
}
