package ma.co.orimex.stock.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StockConteneurReception entity.
 */
public class StockConteneurReceptionDTO implements Serializable {

    private Long id;

    private Integer idOperation;

    private LocalDate dateReception;

    private String numeroOperation;

    private String numeroBonEntree;

    private String numeroConstatNonConformite;

    private Integer isValide;

    private Long stockReceptionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public LocalDate getDateReception() {
        return dateReception;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    public String getNumeroOperation() {
        return numeroOperation;
    }

    public void setNumeroOperation(String numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    public String getNumeroBonEntree() {
        return numeroBonEntree;
    }

    public void setNumeroBonEntree(String numeroBonEntree) {
        this.numeroBonEntree = numeroBonEntree;
    }

    public String getNumeroConstatNonConformite() {
        return numeroConstatNonConformite;
    }

    public void setNumeroConstatNonConformite(String numeroConstatNonConformite) {
        this.numeroConstatNonConformite = numeroConstatNonConformite;
    }

    public Integer getIsValide() {
        return isValide;
    }

    public void setIsValide(Integer isValide) {
        this.isValide = isValide;
    }

    public Long getStockReceptionId() {
        return stockReceptionId;
    }

    public void setStockReceptionId(Long stockReceptionId) {
        this.stockReceptionId = stockReceptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StockConteneurReceptionDTO stockConteneurReceptionDTO = (StockConteneurReceptionDTO) o;
        if (stockConteneurReceptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stockConteneurReceptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockConteneurReceptionDTO{" +
            "id=" + getId() +
            ", idOperation=" + getIdOperation() +
            ", dateReception='" + getDateReception() + "'" +
            ", numeroOperation='" + getNumeroOperation() + "'" +
            ", numeroBonEntree='" + getNumeroBonEntree() + "'" +
            ", numeroConstatNonConformite='" + getNumeroConstatNonConformite() + "'" +
            ", isValide=" + getIsValide() +
            ", stockReception=" + getStockReceptionId() +
            "}";
    }
}
