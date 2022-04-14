import { useState } from 'react';

export default function () {
  const [currentUserId, setCurrentUserId] = useState(null);

  return {
    currentUserId,
    setCurrentUserId,
  };
}
