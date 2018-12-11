package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AchatPrevisionArrivage entity.
 */
public class AchatPrevisionArrivageDTO implements Serializable {

    private Long id;

    private Integer idPrevisionArrivage;

    private String produit;

    private String desigantionCopagnieMaritme;

    private String pol;

    private String numeroBl;

    private Integer nombreTc;

    private LocalDate etd;

    private LocalDate eta;

    private String documents;

    private String douane;

    private Integer active;

    private Long achatDossierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPrevisionArrivage() {
        return idPrevisionArrivage;
    }

    public void setIdPrevisionArrivage(Integer idPrevisionArrivage) {
        this.idPrevisionArrivage = idPrevisionArrivage;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getDesigantionCopagnieMaritme() {
        return desigantionCopagnieMaritme;
    }

    public void setDesigantionCopagnieMaritme(String desigantionCopagnieMaritme) {
        this.desigantionCopagnieMaritme = desigantionCopagnieMaritme;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getNumeroBl() {
        return numeroBl;
    }

    public void setNumeroBl(String numeroBl) {
        this.numeroBl = numeroBl;
    }

    public Integer getNombreTc() {
        return nombreTc;
    }

    public void setNombreTc(Integer nombreTc) {
        this.nombreTc = nombreTc;
    }

    public LocalDate getEtd() {
        return etd;
    }

    public void setEtd(LocalDate etd) {
        this.etd = etd;
    }

    public LocalDate getEta() {
        return eta;
    }

    public void setEta(LocalDate eta) {
        this.eta = eta;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getDouane() {
        return douane;
    }

    public void setDouane(String douane) {
        this.douane = douane;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Long getAchatDossierId() {
        return achatDossierId;
    }

    public void setAchatDossierId(Long achatDossierId) {
        this.achatDossierId = achatDossierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatPrevisionArrivageDTO achatPrevisionArrivageDTO = (AchatPrevisionArrivageDTO) o;
        if (achatPrevisionArrivageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatPrevisionArrivageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatPrevisionArrivageDTO{" +
            "id=" + getId() +
            ", idPrevisionArrivage=" + getIdPrevisionArrivage() +
            ", produit='" + getProduit() + "'" +
            ", desigantionCopagnieMaritme='" + getDesigantionCopagnieMaritme() + "'" +
            ", pol='" + getPol() + "'" +
            ", numeroBl='" + getNumeroBl() + "'" +
            ", nombreTc=" + getNombreTc() +
            ", etd='" + getEtd() + "'" +
            ", eta='" + getEta() + "'" +
            ", documents='" + getDocuments() + "'" +
            ", douane='" + getDouane() + "'" +
            ", active=" + getActive() +
            ", achatDossier=" + getAchatDossierId() +
            "}";
    }
}
