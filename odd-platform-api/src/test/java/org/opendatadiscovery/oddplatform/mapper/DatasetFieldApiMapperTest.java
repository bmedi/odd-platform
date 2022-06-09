package org.opendatadiscovery.oddplatform.mapper;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendatadiscovery.oddplatform.api.contract.model.DataSetField;
import org.opendatadiscovery.oddplatform.api.contract.model.DataSetFieldStat;
import org.opendatadiscovery.oddplatform.api.contract.model.DataSetFieldType;
import org.opendatadiscovery.oddplatform.dto.DatasetFieldDto;
import org.opendatadiscovery.oddplatform.model.tables.pojos.DatasetFieldPojo;
import org.opendatadiscovery.oddplatform.utils.JSONTestUtils;

import static org.jooq.JSONB.jsonb;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DatasetFieldApiMapperTest {

    @InjectMocks
    DatasetFieldApiMapper datasetFieldApiMapper = new DatasetFieldApiMapperImpl(new LabelMapperImpl());

    @Test
    @DisplayName("mapping dataset fields")
    void testDatasetField() {
        final EasyRandom easyRandom = new EasyRandom();
        final DatasetFieldDto datasetFieldDto = easyRandom.nextObject(DatasetFieldDto.class);
        final DataSetFieldStat dataSetFieldStat = easyRandom.nextObject(DataSetFieldStat.class);
        final DataSetFieldType dataSetFieldType = easyRandom.nextObject(DataSetFieldType.class);
        datasetFieldDto.getDatasetFieldPojo().setType(jsonb(JSONTestUtils.createJson(dataSetFieldType)));
        datasetFieldDto.getDatasetFieldPojo().setStats(jsonb(JSONTestUtils.createJson(dataSetFieldStat)));

        final DataSetField actualDataSetField = datasetFieldApiMapper.mapDto(datasetFieldDto);

        assertNotNull(actualDataSetField.getName());
        assertEquals(datasetFieldDto.getEnumValueCount(), actualDataSetField.getEnumValueCount());
        assertEquals(datasetFieldDto.getParentFieldId(), actualDataSetField.getParentFieldId());
        assertNull(actualDataSetField.getDefaultValue());
        assertDataField(datasetFieldDto.getDatasetFieldPojo(), actualDataSetField);
    }

    private void assertDataField(final DatasetFieldPojo expectedDatasetFieldPojo,
                                 final DataSetField actualDataSetField) {
        assertEquals(expectedDatasetFieldPojo.getId(), actualDataSetField.getId());
        assertEquals(expectedDatasetFieldPojo.getName(), actualDataSetField.getName());
        assertEquals(expectedDatasetFieldPojo.getOddrn(), actualDataSetField.getOddrn());
        assertEquals(expectedDatasetFieldPojo.getIsKey(), actualDataSetField.getIsKey());
        assertEquals(expectedDatasetFieldPojo.getIsValue(), actualDataSetField.getIsValue());
        assertEquals(expectedDatasetFieldPojo.getExternalDescription(), actualDataSetField.getExternalDescription());
        assertEquals(expectedDatasetFieldPojo.getInternalDescription(), actualDataSetField.getInternalDescription());
    }
}