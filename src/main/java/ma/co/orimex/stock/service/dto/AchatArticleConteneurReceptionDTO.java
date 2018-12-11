package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the AchatArticleConteneurReception entity.
 */
public class AchatArticleConteneurReceptionDTO implements Serializable {

    private Long id;

    private Integer idArticleConteneurReception;

    private BigDecimal dimension;

    private Integer nombreCaissestc;

    private Integer nombreFeuillecaisse;

    private Long achatConteneurReceptionId;

    private Long produitId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdArticleConteneurReception() {
        return idArticleConteneurReception;
    }

    public void setIdArticleConteneurReception(Integer idArticleConteneurReception) {
        this.idArticleConteneurReception = idArticleConteneurReception;
    }

    public BigDecimal getDimension() {
        return dimension;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

    public Integer getNombreCaissestc() {
        return nombreCaissestc;
    }

    public void setNombreCaissestc(Integer nombreCaissestc) {
        this.nombreCaissestc = nombreCaissestc;
    }

    public Integer getNombreFeuillecaisse() {
        return nombreFeuillecaisse;
    }

    public void setNombreFeuillecaisse(Integer nombreFeuillecaisse) {
        this.nombreFeuillecaisse = nombreFeuillecaisse;
    }

    public Long getAchatConteneurReceptionId() {
        return achatConteneurReceptionId;
    }

    public void setAchatConteneurReceptionId(Long achatConteneurReceptionId) {
        this.achatConteneurReceptionId = achatConteneurReceptionId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO = (AchatArticleConteneurReceptionDTO) o;
        if (achatArticleConteneurReceptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), achatArticleConteneurReceptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AchatArticleConteneurReceptionDTO{" +
            "id=" + getId() +
            ", idArticleConteneurReception=" + getIdArticleConteneurReception() +
            ", dimension=" + getDimension() +
            ", nombreCaissestc=" + getNombreCaissestc() +
            ", nombreFeuillecaisse=" + getNombreFeuillecaisse() +
            ", achatConteneurReception=" + getAchatConteneurReceptionId() +
            ", produit=" + getProduitId() +
            "}";
    }
}
