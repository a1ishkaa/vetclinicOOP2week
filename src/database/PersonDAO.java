package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    // CREATE
    public boolean insertOwner(Owner o) {
        String sql = "INSERT INTO person (name, phone, role, pets_count, specialization) " +
                "VALUES (?, ?, 'OWNER', ?, NULL)";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, o.getName());
            ps.setString(2, o.getPhone());
            ps.setInt(3, o.getPetsCount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Insert owner failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public boolean insertVet(Veterinarian v) {
        String sql = "INSERT INTO person (name, phone, role, pets_count, specialization) " +
                "VALUES (?, ?, 'VET', NULL, ?)";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, v.getName());
            ps.setString(2, v.getPhone());
            ps.setString(3, v.getSpecialization());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Insert vet failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // READ
    public List<Person> getAll() {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM person ORDER BY person_id";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(fromRS(rs));
        } catch (SQLException e) {
            System.out.println("❌ Select all failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    public Person getById(int id) {
        String sql = "SELECT * FROM person WHERE person_id = ?";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return null;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? fromRS(rs) : null;
        } catch (SQLException e) {
            System.out.println("❌ Select by id failed: " + e.getMessage());
            return null;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public List<Person> getOwnersOnly() {
        return getByRole("OWNER");
    }

    public List<Person> getVetsOnly() {
        return getByRole("VET");
    }

    private List<Person> getByRole(String role) {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE role = ? ORDER BY person_id";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(fromRS(rs));
        } catch (SQLException e) {
            System.out.println("❌ Select by role failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    // UPDATE
    public boolean updateOwner(Owner o) {
        String sql = "UPDATE person SET name=?, phone=?, pets_count=? " +
                "WHERE person_id=? AND role='OWNER'";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, o.getName());
            ps.setString(2, o.getPhone());
            ps.setInt(3, o.getPetsCount());
            ps.setInt(4, o.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Update owner failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public boolean updateVet(Veterinarian v) {
        String sql = "UPDATE person SET name=?, phone=?, specialization=? " +
                "WHERE person_id=? AND role='VET'";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, v.getName());
            ps.setString(2, v.getPhone());
            ps.setString(3, v.getSpecialization());
            ps.setInt(4, v.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Update vet failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // DELETE
    public boolean deleteById(int id) {
        String sql = "DELETE FROM person WHERE person_id = ?";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // SEARCH (Week 8)
    public List<Person> searchByName(String part) {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE name ILIKE ? ORDER BY name";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + part + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(fromRS(rs));
        } catch (SQLException e) {
            System.out.println("❌ Search name failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    // numeric search: pets_count BETWEEN min and max (owners only)
    public List<Person> searchOwnersByPetsRange(int min, int max) {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE role='OWNER' AND pets_count BETWEEN ? AND ? " +
                "ORDER BY pets_count DESC";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(fromRS(rs));
        } catch (SQLException e) {
            System.out.println("❌ Search pets range failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return list;
    }

    // helper
    private Person fromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("person_id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String role = rs.getString("role");

        if ("OWNER".equals(role)) {
            int pets = rs.getInt("pets_count");
            return new Owner(id, name, phone, pets);
        } else {
            String spec = rs.getString("specialization");
            return new Veterinarian(id, name, phone, spec);
        }
    }
}