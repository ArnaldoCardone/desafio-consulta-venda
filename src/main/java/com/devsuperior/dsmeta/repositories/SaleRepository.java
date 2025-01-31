package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projection.SaleReportProjection;
import com.devsuperior.dsmeta.projection.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {


   @Query(nativeQuery = true, value = "select tb_seller.name, sum(tb_sales.amount) as sum " +
           " from tb_sales inner join tb_seller on tb_sales.seller_id = tb_seller.id " +
           " where tb_sales.date between :minDate and :maxDate " +
           " group by  tb_seller.name ")
        Page<SaleSummaryProjection> searchSummary(String minDate, String maxDate, Pageable pageable);


    @Query(nativeQuery = true, value = "select tb_sales.id, tb_seller.name, tb_sales.amount, tb_sales.date " +
            " from tb_sales inner join tb_seller on tb_sales.seller_id = tb_seller.id " +
            " where tb_sales.date between :minDate and :maxDate " +
            " and ( :name is Null or UPPER(tb_seller.name) like UPPER(CONCAT('%', :name, '%'))) ",
            countQuery = "SELECT count(*) FROM tb_sales inner join tb_seller on tb_sales.SELLER_ID = tb_seller.id")
    Page<SaleReportProjection> searchReport(String minDate, String maxDate, String name, Pageable pageable);
}
