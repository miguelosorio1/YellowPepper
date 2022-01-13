// Call the dataTables jQuery plugin
$(document).ready(function() {

    loadAccounts();
  $('#dataTable').DataTable();
});


async function loadAccounts() {

      const rawResponse = await fetch('accounts', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
       // body: JSON.stringify({a: 1, b: 'Textual content'})
      });
      const accounts = await rawResponse.json();

      let listHTML = ''
        for (let account of accounts){
            let accountHtml = '<tr> <td>'+account.account_number+
                '</td> <td>'+account.account_balance+
                '</td> <td></td> <td></td> </tr>'
            listHTML+=accountHtml;
        }
      console.log(accounts);

      document.querySelector('#dataTable tbody').outerHTML = listHTML;
}