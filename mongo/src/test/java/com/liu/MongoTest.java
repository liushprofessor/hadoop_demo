package com.liu;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Liush
 * @description
 * @date 2019/5/15 0015 13:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    CustomerRepository repository;


    Customer dave, oliver, carter;

    @Test
    public void createCollection(){

        repository.deleteAll();
        dave = repository.save(new Customer("Dave", "Matthews"));
        oliver = repository.save(new Customer("Oliver August", "Matthews"));
        carter = repository.save(new Customer("Carter", "Beauford"));
    }

    @Test
    public void search(){

        List<Customer> list=repository.findByLastnameAndFirstname("Matthews","Dave");

        for (Customer customer:list){

            System.out.println(customer.getFirstname());
        }


    }

    @Test
    public void searchAscAndDsc(){

        List<Customer> list=repository.findByLastnameOrderByFirstnameAsc("Matthews");

        List<Customer> list2=repository.findByLastnameOrderByFirstnameDesc("Matthews");

        System.out.println();
    }


    @Test
    public void searchPage(){

        Page<Customer> list=repository.findPagedBy(new Pageable() {
            @Override
            public int getPageNumber() {
                return 1;
            }

            @Override
            public int getPageSize() {
                return 1;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return  Sort.by(Sort.Direction.DESC,"firstname");
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        });

        System.out.println(list);

    }


    @Test
    public void searchDto(){

        List<CustomerDto> list=repository.findAllBy();
        System.out.println(list);
    }

    @Test
    public void searchProject(){
        Customer customer=repository.findProjectedByFirstname("Dave",Customer.class);
        System.out.println(customer);
    }


    @Test
    public void searchCustomerById(){
        Customer customer=repository.findCustimerById(new ObjectId("5cdbbc016aec3127c00a75d7"));
        System.out.println(customer);
    }



}
