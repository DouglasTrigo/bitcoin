package br.com.alura.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
/*Com @UserDefinition estou falando que é esta entidade que tem as informações, usuário, senha e role,
* só pode ter uma por sistema e ela tem que ser uma entidade JPA*/
@UserDefinition
@Getter
@Setter
public class Usuario extends PanacheEntityBase {

    /*Esta classe está usando o padrão Active Record
    * É um padrão de projeto que trabalha com a técnica ORM (Object Relational Mapper).
    * O modelo consiste em utilizar as entidades
    * (Classe java que representa o objeto do mundo real no java)
    * para chamar os métodos de persistência de dados.
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private String cpf;

    @Username/*Tem que ser String*/
    private String username;

    @Getter(AccessLevel.NONE)
    @Password/*Tem que ser String e obriga a incriptar a senha*/
    private String password;

    @Roles/*Tem que ser String*/
    private String role;

    public static void adicionar(Usuario usuario){
        usuario.password = BcryptUtil.bcryptHash(usuario.password);
        usuario.role = validarUsername(usuario.username);
        usuario.persist();
    }

    private static String validarUsername(String username) {
        if(username.equals("alura")){
            return "admin";
        }
        return "user";
    }

    @JsonbTransient//Quando for serializar, o campo de senha vai ser ignorado
    public String getPassword() {
        return password;
    }
}
