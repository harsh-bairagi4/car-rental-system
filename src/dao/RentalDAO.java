/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import model.Rental;
import java.util.Date;
import java.util.List;
/**
 *
 * @author harsh
 */
public interface RentalDAO {
    void createRental(int carId, int customerId, Date startDate, int days, double totalCost) throws Exception;
    Rental getActiveRentalByCarId(int carId)throws Exception;
    void markAsReturned(int rentalId, double finalCost)throws Exception;
    List<Rental> getAllActiveRentals()throws Exception;
    void deleteRental(int id)throws Exception;
}
