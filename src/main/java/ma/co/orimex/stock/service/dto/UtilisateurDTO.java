package ma.co.orimex.stock.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Utilisateur entity.
 */
public class UtilisateurDTO implements Serializable {

    private Long id;

    private Integer idUtilisateur;

    private String loginUtilisateur;

    private String matriculeUtilisateur;

    private String nomUtilsateur;

    private String passwordUtilisateur;

    private String prenomUtilsateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getMatriculeUtilisateur() {
        return matriculeUtilisateur;
    }

    public void setMatriculeUtilisateur(String matriculeUtilisateur) {
        this.matriculeUtilisateur = matriculeUtilisateur;
    }

    public String getNomUtilsateur() {
        return nomUtilsateur;
    }

    public void setNomUtilsateur(String nomUtilsateur) {
        this.nomUtilsateur = nomUtilsateur;
    }

    public String getPasswordUtilisateur() {
        return passwordUtilisateur;
    }

    public void setPasswordUtilisateur(String passwordUtilisateur) {
        this.passwordUtilisateur = passwordUtilisateur;
    }

    public String getPrenomUtilsateur() {
        return prenomUtilsateur;
    }

    public void setPrenomUtilsateur(String prenomUtilsateur) {
        this.prenomUtilsateur = prenomUtilsateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UtilisateurDTO utilisateurDTO = (UtilisateurDTO) o;
        if (utilisateurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisateurDTO{" +
            "id=" + getId() +
            ", idUtilisateur=" + getIdUtilisateur() +
            ", loginUtilisateur='" + getLoginUtilisateur() + "'" +
            ", matriculeUtilisateur='" + getMatriculeUtilisateur() + "'" +
            ", nomUtilsateur='" + getNomUtilsateur() + "'" +
            ", passwordUtilisateur='" + getPasswordUtilisateur() + "'" +
            ", prenomUtilsateur='" + getPrenomUtilsateur() + "'" +
            "}";
    }
}
