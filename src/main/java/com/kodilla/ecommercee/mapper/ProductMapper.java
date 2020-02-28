package com.kodilla.ecommercee.mapper;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


}

/*
@Autowired
    TitleCopyRepository titleCopyRepository;

    @Autowired
    UserRepository userRepository;

    public Loan mapToLoan(final LoanDto loanDto) {
        return new Loan(
                loanDto.getLoanId(),
                titleCopyRepository.findByCopyId(loanDto.getCopyId()),
                userRepository.findByUserId(loanDto.getUserId()),
                loanDto.getLoanedDate(),
                loanDto.getReturnedDate());
    }

    public LoanDto mapToLoanDto(final Loan loan) {
        return new LoanDto(
                loan.getLoanId(),
                loan.getTitleCopy().getCopyId(),
                loan.getLibraryUser().getUserId(),
                loan.getLoanedDate(),
                loan.getReturnedDate());
    }

    public List<LoanDto> mapToLoanDtoList(final List<Loan> loanList) {
        return loanList.stream()
                .map(l -> new LoanDto(l.getLoanId(), l.getTitleCopy().getCopyId(), l.getLibraryUser().getUserId(), l.getLoanedDate(), l.getReturnedDate()))
                .collect(Collectors.toList());
    }
 */