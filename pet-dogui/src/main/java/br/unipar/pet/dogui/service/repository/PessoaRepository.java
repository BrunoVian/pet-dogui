package br.unipar.pet.dogui.service.repository;

import br.unipar.pet.dogui.model.Endereco;
import br.unipar.pet.dogui.model.Pessoa;
import br.unipar.pet.dogui.model.Pet;
import br.unipar.pet.dogui.utils.ConnectionFactory;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    private Connection connection;

    private static final String INSERT
            = "INSERT INTO pessoa (nomePessoa, telefone, email) VALUES (?, ?, ?);";
    
    private static final String FIND_ALL
            = "SELECT * FROM pessoa";
    
    private static final String FIND_BY_ID
            ="SELECT * FROM pessoa WHERE id = ?";
    
    private static final String UPDATE
            ="UPDATE pessoa SET nomePessoa = ?, telefone = ?, email = ? WHERE id = ?";
    
    private static final String DELETE
            ="DELETE FROM pessoa WHERE id = ?";
    
    private static final String FIND_ENDERECO_BY_PESSOA_ID
            ="SELECT * FROM endereco WHERE id_pessoa = ?";

    

    public Pessoa insert(Pessoa pessoa) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(INSERT,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getNrTelefone());
            ps.setString(3, pessoa.getEmail());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            rs.next();
            pessoa.setId(rs.getInt(1));

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

        return pessoa;

    }
    
    public ArrayList<Pessoa> findAll() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pessoa> listaResultado = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {

                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nomePessoa"));
                pessoa.setNrTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                
                pessoa.setListaEnderecos(findEnderecosByPessoaId(pessoa.getId()));

                listaResultado.add(pessoa);
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
    
    private ArrayList<Endereco> findEnderecosByPessoaId(int pessoaId) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Endereco> enderecos = new ArrayList<>();
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_ENDERECO_BY_PESSOA_ID);

            ps.setInt(1, pessoaId);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setNomeRua(rs.getString("nomeRua"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setDsBairro(rs.getString("bairro"));
                endereco.setNrCasa(rs.getInt("numero"));
                endereco.setNrCep(rs.getString("cep"));
                endereco.setStAtivo(rs.getBoolean("stAtivo"));

                enderecos.add(endereco);
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

        return enderecos;

    }

    public Pessoa findById(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pessoa resultado = new Pessoa();

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                resultado.setId(rs.getInt("id"));
                resultado.setNome(rs.getString("nomePessoa"));
                resultado.setNrTelefone(rs.getString("telefone"));
                resultado.setEmail(rs.getString("email"));
                resultado.setListaEnderecos(findEnderecosByPessoaId(resultado.getId()));
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
    
    public ArrayList<Pessoa> findWithParameters(String nome) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pessoa> listaResultado = new ArrayList<>();

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(FIND_ALL
                    + " where nomepessoa like '%" + nome + "%'");
            System.out.println(ps.toString());
            rs = ps.executeQuery();

            while (rs.next()) {

                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nomePessoa"));
                pessoa.setEmail("email");
                pessoa.setNrTelefone(rs.getString("telefone"));
                pessoa.setListaEnderecos(findEnderecosByPessoaId(pessoa.getId()));

                listaResultado.add(pessoa);
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

    public Pessoa update(Pessoa pessoa) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getNrTelefone());
            ps.setString(3, pessoa.getEmail());
            ps.setInt(4, pessoa.getId());
            ps.execute();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return pessoa;

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
}