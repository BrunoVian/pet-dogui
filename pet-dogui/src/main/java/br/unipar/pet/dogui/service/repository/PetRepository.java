package br.unipar.pet.dogui.service.repository;

import br.unipar.pet.dogui.model.Pessoa;
import br.unipar.pet.dogui.model.Pet;
import br.unipar.pet.dogui.model.enums.GeneroEnum;
import br.unipar.pet.dogui.model.enums.PorteEnum;
import br.unipar.pet.dogui.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PetRepository {
    private static final String INSERT
            = "INSERT INTO pet (nomePet, dono_id, genero, peso, porte) VALUES(?, ?, ?, ?, ?);";
    private static final String UPDATE
            = "UPDATE pet SET nomePet=?, dono_id=?, genero=?, peso=?, porte=?"
            + "WHERE id= ? ;";
    private static final String DELETE
            = "DELETE from pet WHERE id=? ;";
    private static final String FIND_BY_ID
            = "SELECT id, nomePet, dono_id, genero, peso, porte FROM pet where id = ?;";
    private static final String FIND_ALL
            = "SELECT * FROM pet";

    public Pet insert(Pet pet) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getNome());
            ps.setInt(2, pet.getDono().getId());
            ps.setString(3, pet.getGenero().toString());
            ps.setDouble(4, pet.getPeso());
            ps.setString(5, pet.getPorte().toString());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            rs.next();
            pet.setId(rs.getInt(1));

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return pet;

    }

    public ArrayList<Pet> findAll() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pet> listaResultado = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {

                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nomePet"));
                pet.setGenero(GeneroEnum.valueOf(rs.getString("genero")));
                pet.setPeso(rs.getDouble("peso"));
                pet.setPorte(PorteEnum.valueOf(rs.getString("porte")));

                int donoId = rs.getInt("dono_id");
                Pessoa dono = new PessoaRepository().findById(donoId);
                pet.setDono(dono);

                listaResultado.add(pet);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listaResultado;

    }

    public ArrayList<Pet> findWithParameters(String descricao) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pet> listaResultado = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(FIND_ALL
                    + " where nomepet like '%" + descricao + "%'");
            System.out.println(ps.toString());
            rs = ps.executeQuery();

            while (rs.next()) {

                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nomePet"));
                pet.setGenero(GeneroEnum.valueOf(rs.getString("genero")));
                pet.setPeso(rs.getDouble("peso"));
                pet.setPorte(PorteEnum.valueOf(rs.getString("porte")));

                int donoId = rs.getInt("dono_id");
                Pessoa dono = new PessoaRepository().findById(donoId);
                pet.setDono(dono);

                listaResultado.add(pet);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listaResultado;

    }

    public void delete(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.execute();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public Pet update(Pet pet) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, pet.getNome());
            ps.setInt(2, pet.getDono().getId());
            ps.setString(3, pet.getGenero().toString());
            ps.setDouble(4, pet.getPeso());
            ps.setString(5, pet.getPorte().toString());
            ps.setInt(6, pet.getId());
            ps.execute();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return pet;

    }

    public Pet findById(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pet resultado = new Pet();

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                resultado.setId(rs.getInt("id"));
                resultado.setNome(rs.getString("nomePet"));
                resultado.setGenero(GeneroEnum.valueOf(rs.getString("genero")));
                resultado.setPeso(rs.getDouble("peso"));
                resultado.setPorte(PorteEnum.valueOf(rs.getString("porte")));

                int donoId = rs.getInt("dono_id");
                Pessoa dono = new PessoaRepository().findById(donoId);
                resultado.setDono(dono);

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return resultado;
    }
}
