function isCancelledBookings(data, orderId) {
  if (data.length === 0 || orderId===undefined)
    return false
  for(var i=0;i<data.length;i++)
  {
    if(data[i].orderIdToBeShown === orderId){
      return true
    }
  }
  return false
}
