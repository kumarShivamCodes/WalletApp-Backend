package com.nexxtuple.walletapp.controller;

import com.nexxtuple.walletapp.dto.*;
import com.nexxtuple.walletapp.entity.Transaction;
import com.nexxtuple.walletapp.service.TransactionService;
import com.nexxtuple.walletapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers()
    {
        List<UserOutputDto> users=userService.getAllUsers().stream().map(
                user-> modelMapper.map(user,UserOutputDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserOutputDto> getUser(@RequestBody UserInputLoginDto userInputLoginDto){
       UserOutputDto userOutputDto=userService.getUser(userInputLoginDto.getUsername(),userInputLoginDto.getPassword());
       return new ResponseEntity<>(userOutputDto,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserOutputDto> addUser(@RequestBody UserInputRegisterDto userInputRegisterDto)
    {
        UserOutputDto userOutputDto=userService.addUser(userInputRegisterDto);
        return new ResponseEntity<>(userOutputDto,HttpStatus.CREATED);
    }

    @PatchMapping("/recharge")
    public ResponseEntity<UserOutputDto> rechargeBalance(@RequestBody UserInputBalanceDto userInputBalanceDto)
    {
        UserOutputDto userOutputDto=userService.updateUserBalance(userInputBalanceDto.getUsername(),userInputBalanceDto.getAmount(), userInputBalanceDto.getAccNo());
        return new ResponseEntity<>(userOutputDto,HttpStatus.OK);
    }
    @PatchMapping("/transfer")
    public ResponseEntity<UserOutputDto> transferAmount(@RequestBody UserInputBalanceDto userInputBalanceDto)
    {
        UserOutputDto userOutputDto=userService.transferAmount(userInputBalanceDto.getUsername(),userInputBalanceDto.getAmount(),userInputBalanceDto.getAccNo());
        return new ResponseEntity<>(userOutputDto,HttpStatus.OK);
    }

    @GetMapping("/transactions/{username}")
    public ResponseEntity<List<TransactionOutputDto>> getAllTransactionsByUsername(@PathVariable String username)
    {
        List<TransactionOutputDto> transactions=transactionService.getAllTransactionsByUsername(username).stream().map(
                transaction -> modelMapper.map(transaction,TransactionOutputDto.class)).collect(Collectors.toList());

        return  new ResponseEntity<>(transactions,HttpStatus.OK);
    }

}
