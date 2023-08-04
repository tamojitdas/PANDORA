package com.science.earth.biogeochemistry.freshwaters.pandora.services.mapcrudservices.implementations;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.science.earth.biogeochemistry.freshwaters.pandora.general.objects.CellBaseObject;

@Service
public class YMapServiceImpl extends AbstractHashMapService implements YMapService {    
    @Override
    public double[] findAtCellAndTimestep(CellBaseObject cell, LocalDateTime t) {
	return map.get(hashCellAndTime(cell, t));
    }

    @Override
    public void saveAtCellAndTimestep(CellBaseObject cell, LocalDateTime tEnd, double[] yEnd) {
	map.put(hashCellAndTime(cell, tEnd), yEnd);
    }
}
