import { Grid } from '@mui/material';
import styled from 'styled-components';

export const TestReportBySuitNameHeader = styled(Grid)(({ theme }) => ({
  cursor: 'pointer',
  flexWrap: 'nowrap',
  alignItems: 'center',
  paddingBottom: theme.spacing(0.75),
}));
