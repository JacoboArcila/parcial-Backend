package com.dh.model.service;

import com.dh.dao.IDao;
import com.dh.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public List<Odontologo> buscarTodos() throws Exception {
        return odontologoIDao.buscarTodos();
    }

    public Odontologo guardar(Odontologo odontologo) throws Exception {
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo buscarId(Integer id) throws Exception   {
        return odontologoIDao.buscarId(id);
    }

}
