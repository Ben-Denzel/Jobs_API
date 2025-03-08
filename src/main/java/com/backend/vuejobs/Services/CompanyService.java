package com.backend.vuejobs.Services;

import com.backend.vuejobs.Models.Company;
import com.backend.vuejobs.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            company.setName(companyDetails.getName());
            company.setDescription(companyDetails.getDescription());
            company.setContactEmail(companyDetails.getContactEmail());
            company.setContactPhone(companyDetails.getContactPhone());
            return companyRepository.save(company);
        }
        return null;
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}