import './styles.css';
import 'react-datepicker/dist/react-datepicker.css';

import ptBR from 'date-fns/locale/pt-BR';
import { useEffect, useState } from 'react';
import DatePicker, { registerLocale } from 'react-datepicker';

import NotificationButton from '../NotificationButton';
import axios from 'axios';
import { BASE_URL } from '../../utils/request';
import { Sale } from '../../models/sales';

registerLocale('pt-BR', ptBR);

function SalesCard() {
    const min = new Date(new Date().setDate(new Date().getDate() - 30));
    const max = new Date();

    const [minDate, setMinDate] = useState(min);
    const [maxDate, setMaxDate] = useState(max);

    const [sales, setSales] = useState<Sale[]>([]);

    useEffect(() => {
        axios.get(`${BASE_URL}/sales`)
            .then(response => {
                setSales(response.data.content);
            })
    }, [])

    return (
        <div className="dsmeta-card">
            <h2 className="dsmeta-sales-title">Vendas</h2>

            <div>
                <div className="dsmeta-form-control-container">
                    <DatePicker
                        className="dsmeta-form-control"
                        locale="pt-BR"
                        selected={minDate}
                        onChange={(date: Date) => setMinDate(date)}
                        dateFormat="dd/MM/yyyy" />
                </div>

                <div className="dsmeta-form-control-container">
                    <DatePicker
                        className="dsmeta-form-control"
                        selected={maxDate}
                        onChange={(date: Date) => setMaxDate(date)}
                        dateFormat="dd/MM/yyyy" />
                </div>
            </div>

            <div>
                <table className="dsmeta-sales-table">
                    <thead>
                        <tr>
                            <th className="show992">ID</th>
                            <th className="show576">Data</th>
                            <th>Vendedor</th>
                            <th className="show992">Visitas</th>
                            <th className="show992">Vendas</th>
                            <th>Total</th>
                            <th>Notificar</th>
                        </tr>
                    </thead>

                    <tbody>
                        {sales.map(sale => {
                            return (
                                <tr key={sale.id}>
                                    <td className="show992">#{sale.id}</td>
                                    <td className="show576">{new Date(sale.date).toLocaleDateString()}</td>
                                    <td>{sale.sellerName}</td>
                                    <td className="show992">{sale.visited}</td>
                                    <td className="show992">{sale.deals}</td>
                                    <td>R$ {sale.amount.toFixed(2)}</td>
                                    <td>
                                        <div className="dsmeta-red-btn-container">
                                            <NotificationButton />
                                        </div>
                                    </td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default SalesCard;